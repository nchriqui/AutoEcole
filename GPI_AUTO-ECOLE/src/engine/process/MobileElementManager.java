package engine.process;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;


public class MobileElementManager {
	private Map map;
	private Car car;

	public MobileElementManager(Map map) {
		super();
		this.map = map;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void moveLeftCar() {
		Block position = car.getPosition();
		//DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if (  ( (position.getLine() == 1 || position.getLine() == 2 || position.getLine() == 9 || position.getLine() == 10 ) &&   ( position.getColumn() >1 && position.getColumn()<=22) ) || ( (position.getLine()>=1 && position.getLine()<=10) && ( position.getColumn()==2 || position.getColumn()==23 )  ) ) {
			//(position.getColumn() >= 1 && position.getColumn() <=23)) || ((position.getLine() >= 1 && position.getLine() <= 9) && ((position.getColumn() >= 1 && position.getColumn() <=2) || (position.getColumn() >= 22 && position.getColumn() <=23) ))) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn()-1);
			car.setPosition(newPosition);
		}
	}
	
	
	public void moveRightCar() {
		Block position = car.getPosition();
		//DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if (  ( (position.getLine() == 1 || position.getLine() == 2 || position.getLine() == 9 || position.getLine() == 10 ) &&   ( position.getColumn() >=1 && position.getColumn()<=21) ) || ( (position.getLine()>=1 && position.getLine()<=10) && ( position.getColumn()==1 || position.getColumn()==22 )  ) ) {
				//(position.getColumn() >= 1 && position.getColumn() <=23)) || ((position.getLine() >= 1 && position.getLine() <= 9) && ((position.getColumn() >= 1 && position.getColumn() <=2) || (position.getColumn() >= 22 && position.getColumn() <=23) ))) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn()+1);
			car.setPosition(newPosition);
		}
	}


	public void moveDownCar() {
		Block position = car.getPosition();
		//DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if (((position.getLine() == 1 || position.getLine() == 9) && (position.getColumn() >= 1 && position.getColumn() <=23)) || ((position.getLine() >= 1 && position.getLine() <= 9) && ((position.getColumn() >= 1 && position.getColumn() <=2) || (position.getColumn() >= 22 && position.getColumn() <=23) ))) {
			Block newPosition = map.getBlock(position.getLine()+1, position.getColumn());
			car.setPosition(newPosition);
		}
	}

	public void moveUpCar() {
		Block position = car.getPosition();
		//DEPLACEMENT ENTRE LES MURS DU CIRCUIT
		if (((position.getLine() == 2 || position.getLine() == 10) && (position.getColumn() >=1 && position.getColumn() <=22)) || ((position.getLine() >= 2 && position.getLine() <= 10) && ((position.getColumn() >= 1 && position.getColumn() <=2) || (position.getColumn() >= 22 && position.getColumn() <=23) ))) {
			Block newPosition = map.getBlock(position.getLine()-1, position.getColumn());
			car.setPosition(newPosition);
		}
	}

}
