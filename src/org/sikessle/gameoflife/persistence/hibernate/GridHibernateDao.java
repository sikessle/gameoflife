package org.sikessle.gameoflife.persistence.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.sikessle.gameoflife.model.Grid;
import org.sikessle.gameoflife.persistence.GridDao;
import org.sikessle.gameoflife.persistence.util.DtoAndDomainObjectConverter;
import org.sikessle.gameoflife.persistence.util.GridDto;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class GridHibernateDao implements GridDao {

	private final DtoAndDomainObjectConverter converter;
	private Transaction currentTransaction;
	private Session currentSession;

	@Inject
	public GridHibernateDao(Injector injector) {
		converter = new DtoAndDomainObjectConverter(injector);
	}

	@Override
	public Grid getByName(String gameName) {
		if (gameName == null) {
			throw new NullPointerException();
		}
		List<GridDto> found = findByName(gameName);

		if (!found.isEmpty()) {
			return converter.convertDtoToDomain(found.get(0));
		}
		return null;
	}

	@Override
	public boolean saveOrUpdate(Grid grid, String gameName) {
		if (grid == null || gameName == null) {
			throw new NullPointerException();
		}
		GridDto gridDto = converter.convertDomainToDto(grid, gameName);

		try {
			setupTransactionAndSession();

			currentSession.saveOrUpdate(gridDto);

			currentTransaction.commit();
			return true;
		} catch (HibernateException ex) {
			rollbackCurrentTransaction(ex);
		}

		return false;
	}

	@Override
	public boolean delete(String gameName) {
		if (gameName == null) {
			throw new NullPointerException();
		}
		List<GridDto> found = findByName(gameName);
		if (found.isEmpty()) {
			return false;
		}

		try {
			deleteGridsUnchecked(found);
			return true;
		} catch (HibernateException ex) {
			rollbackCurrentTransaction(ex);
		}

		return false;
	}

	private void deleteGridsUnchecked(List<GridDto> found) {
		setupTransactionAndSession();

		for (GridDto grid : found) {
			currentSession.delete(grid);
		}

		currentTransaction.commit();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllGameNames() {
		List<String> result = new LinkedList<String>();
		try {
			setupTransactionAndSession();

			List<GridDto> grids = currentSession.createCriteria(
					GridHibernateDto.class).list();

			for (GridDto grid : grids) {
				result.add(grid.getGameName());
			}

			currentTransaction.commit();
		} catch (HibernateException ex) {
			rollbackCurrentTransaction(ex);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private List<GridDto> findByName(final String gameName) {
		List<GridDto> result = new LinkedList<GridDto>();
		try {
			setupTransactionAndSession();

			result = currentSession.createCriteria(GridHibernateDto.class)
					.add(Restrictions.idEq(gameName)).list();

			currentTransaction.commit();
		} catch (HibernateException ex) {
			rollbackCurrentTransaction(ex);
		}
		return result;
	}

	private void setupTransactionAndSession() throws HibernateException {
		currentSession = HibernateUtil.getInstance().getCurrentSession();
		currentTransaction = currentSession.beginTransaction();
	}

	private void rollbackCurrentTransaction(
			HibernateException transactionException) {
		if (currentTransaction != null) {
			try {
				currentTransaction.rollback();
			} catch (HibernateException exRb) {
				throw new RuntimeException(transactionException.getMessage());
			}
		}
	}
}
