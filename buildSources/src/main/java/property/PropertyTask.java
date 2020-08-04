package property;

import org.gradle.api.*;
import org.gradle.api.logging.*;
import org.gradle.api.tasks.*;

public class propertyTask extend DefaultTask{
	
	private String expected;
	private String actual;

	@Input
	public String getExpected(){
		return expected;
	}
	public void setExpected(String expected){
		this.expected = expected;
	}	

	@Input
	public String getActual(){
		return actual;
	}
	public void setActual(String actual){
		this.actual = actual;
	}

	@TaskAction
	public void giveProperty(){
		getLogger().lifecycle("Give property");
		String expected = (String)(getProgect().getProperties().get("expected"));
		String actual = (String)(getProgect().getProperties().get("actual"));		
	}
}
