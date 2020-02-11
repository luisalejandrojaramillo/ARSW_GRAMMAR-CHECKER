package edu.eci.arsw.blueprints.services;

import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;

public class BlueprintServiceMain {
	public static void main(String a[]) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        Blueprint tempo=new  Blueprint("fer","xd");
        bps.addNewBlueprint(tempo);
        Blueprint tempo1=new  Blueprint("fer","blue1");
        bps.addNewBlueprint(tempo1);
        try {
			Blueprint prueba=bps.getBlueprint("fer","xd");
			System.out.println(prueba.getAuthor());
			HashSet<Blueprint> prueba1=(HashSet<Blueprint>) bps.getBlueprintsByAuthor("fer");
			for(Blueprint bp:prueba1) {
				System.out.println(bp.getName());
			}
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

    }
}
