package org.sikessle.gameoflife.dsl.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import org.sikessle.gameoflife.dsl.menu.Model

/**
 * Generates code from your model files on save.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
class MenuGenerator implements IGenerator {
    
    Model model;
    
    override void doGenerate(Resource resource, IFileSystemAccess fsa) {
        model = resource.allContents.filter(typeof(Model)).next 
        fsa.generateFile("org/sikessle/gameoflie/view/gui/MenuBarGenerated.java", model.compile)
    }
    
    def compile(Model model) '''
        package org.sikessle.gameoflife.view.gui;
        
        «buildImports()»
        
        public class MenuBarGenerated extends JMenuBar {

            «buildConstructorAndFields»
            «buildMenuBuilderMethod»
        }
    '''
    
    def buildImports() '''
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        
        import javax.swing.JMenu;
        import javax.swing.JMenuBar;
        import javax.swing.JMenuItem;
        
        import org.sikessle.gameoflife.controller.GridController;
        import org.sikessle.gameoflife.model.*;
        import org.sikessle.gameoflife.model.impl.*;
    '''
    
    def buildConstructorAndFields()'''
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
    '''
    
    def buildMenuBuilderMethod() '''
        private void buildMenuBar() {
            // build a menu
            «FOR menu:model.menu»
                JMenu «menu.name.toFirstLower» = new JMenu("«menu.name»");
                
                // fill menu with items
                «FOR item:menu.menuItem»
                    JMenuItem «item.name.toFirstLower» = new JMenuItem("«item.name»");
                    «item.name.toFirstLower».addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            «item.action»
                        }
                    });
                    // add item to menu
                    «menu.name.toFirstLower».add(«item.name.toFirstLower»);
                «ENDFOR»
                
                // add menu to menubar
                add(«menu.name.toFirstLower»);
            «ENDFOR»
        }
    '''
    
}