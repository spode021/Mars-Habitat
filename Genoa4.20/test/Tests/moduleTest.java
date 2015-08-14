package Tests;

import finalProject.client.Module;
import static org.junit.Assert.*;

import org.junit.Test;

public class moduleTest {
	private static Module mod = new Module();
	
	@Test
	public void code() {
		mod.setCode(2);
		assertEquals(mod.getCode(), Integer.valueOf(2));
	}
	
	@Test
	public void xCoor() {
		mod.setX(2.2);
		assertEquals(mod.getX(), Double.valueOf(2.2));
	}
	
	@Test
	public void yCoor() {
		mod.setY(2.2);
		assertEquals(mod.getY(), Double.valueOf(2.2));
	}
	
	@Test
	public void status() {
		
	}
	
	@Test
	public void orient() {
		
	}
	
	@Test
	public void type(){
		
	}
	
}