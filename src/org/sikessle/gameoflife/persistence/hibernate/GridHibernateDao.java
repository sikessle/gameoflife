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

		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(gridDto);

			tx.commit();
			return true;
		} catch (HibernateException ex) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException exRb) {
					throw new RuntimeException(ex.getMessage());
				}
			}
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

		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();

			for (GridDto grid : found) {
				session.delete(grid);
			}

			tx.commit();
			return true;
		} catch (HibernateException ex) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException exRb) {
					throw new RuntimeException(ex.getMessage());
				}
			}
		}

		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllGameNames() {
		List<String> result = new LinkedList<String>();
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();

			List<GridDto> grids = session
					.createCriteria(GridHibernateDto.class).list();

			for (GridDto grid : grids) {
				result.add(grid.getGameName());
			}

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException exRb) {
					throw new RuntimeException(ex.getMessage());
				}
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private List<GridDto> findByName(final String gameName) {
		List<GridDto> result = new LinkedList<GridDto>();
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();

			result = session.createCriteria(GridHibernateDto.class)
					.add(Restrictions.idEq(gameName)).list();

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException exRb) {
					throw new RuntimeException(ex.getMessage());
				}
			}
		}
		return result;
	}
}
