package org.sikessle.gameoflife.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.sikessle.gameoflife.controller.GridController;
import org.sikessle.gameoflife.model.*;
import org.sikessle.gameoflife.model.impl.*;

public class MenuBarGenerated extends JMenuBar {

    private static final long serialVersionUID = -1L;
    
    private final GridController controller;
    private final GridDrawingPanel gridPanel;
    
    public MenuBarGenerated(GridController controller, GridDrawingPanel gridPanel) {
        if (controller == null || gridPanel == null) {
            throw new IllegalArgumentException();
        }
    
        this.controller = controller;
        this.gridPanel = gridPanel;
        buildMenuBar();
    }
    private void buildMenuBar() {
        // build a menu
        JMenu loadSave = new JMenu("LoadSave");
        
        // fill menu with items
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoadDialog(controller);
            }
        });
        // add item to menu
        loadSave.add(load);
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SaveDialog(controller);
            }
        });
        // add item to menu
        loadSave.add(save);
        
        // add menu to menubar
        add(loadSave);
        JMenu figures = new JMenu("Figures");
        
        // fill menu with items
        JMenuItem glider = new JMenuItem("Glider");
        glider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FigureSpawnOneTimeMouseAdapter(new Glider(), controller, gridPanel);
            }
        });
        // add item to menu
        figures.add(glider);
        JMenuItem lightWeightSpaceship = new JMenuItem("LightWeightSpaceship");
        lightWeightSpaceship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FigureSpawnOneTimeMouseAdapter(new LightWeightSpaceship(), controller, gridPanel);
            }
        });
        // add item to menu
        figures.add(lightWeightSpaceship);
        JMenuItem rPentomino = new JMenuItem("rPentomino");
        rPentomino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FigureSpawnOneTimeMouseAdapter(new RPentomino(), controller, gridPanel);
            }
        });
        // add item to menu
        figures.add(rPentomino);
        
        // add menu to menubar
        add(figures);
    }
}
