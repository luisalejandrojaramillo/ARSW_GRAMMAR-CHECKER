/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }
    @Test
    public void saveBlueprins() {
    	 InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();        
         Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
         Blueprint bp=new Blueprint("fernando", "music",pts);
         Blueprint bp1=new Blueprint("luis", "video",pts);
         Blueprint bp2=new Blueprint("carlos", "foto",pts);
         Blueprint bp3=new Blueprint("juan", "pintura",pts);
         try {
			ibpp.saveBlueprint(bp);
			ibpp.saveBlueprint(bp1);
			ibpp.saveBlueprint(bp2);
			ibpp.saveBlueprint(bp3);
			assertEquals((ibpp.getAllBlueprints()).size(),5);
		} catch (BlueprintPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @Test
    public void loadBlueprins() {
    	 InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();        
         Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
         Blueprint bp=new Blueprint("fernando", "music",pts);
         Blueprint bp1=new Blueprint("luis", "video",pts);
         Blueprint bp2=new Blueprint("carlos", "foto",pts);
         Blueprint bp3=new Blueprint("juan", "pintura",pts);
         try {
        	ibpp.saveBlueprint(bp);
 			ibpp.saveBlueprint(bp1);
 			ibpp.saveBlueprint(bp2);
 			ibpp.saveBlueprint(bp3);
			Blueprint bp4=ibpp.getBlueprint("fernando", "music");
			System.out.println(bp4.getAuthor());
			assertEquals(bp4.getAuthor(),"fernando");
			Blueprint bp5=ibpp.getBlueprint("juan", "pintura");
			assertEquals(bp5.getAuthor(),"juan");
			Blueprint bp6=ibpp.getBlueprint("carlos", "foto");
			assertEquals(bp6.getName(),"foto");
         }	
		catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BlueprintPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         
    	
    }
    @Test
    public void loadBlueprinsOfAuthorSpecific() {
    	 InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();        
         Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
         Blueprint bp=new Blueprint("fernando", "music",pts);
         Blueprint bp1=new Blueprint("luis", "video",pts);
         Blueprint bp2=new Blueprint("carlos", "foto",pts);
         Blueprint bp3=new Blueprint("juan", "pintura",pts);
         try {
        	ibpp.saveBlueprint(bp);
 			ibpp.saveBlueprint(bp1);
 			ibpp.saveBlueprint(bp2);
 			ibpp.saveBlueprint(bp3);
			assertEquals((ibpp.getBlueprintsByAuthor("juan")).size(),1);
         }	
		catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BlueprintPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    
}
