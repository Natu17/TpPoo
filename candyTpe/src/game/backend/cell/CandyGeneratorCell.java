package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CandyGeneratorCell extends Cell {
	double prob;
	List<Class<?>> specialElements;

	public CandyGeneratorCell(Grid grid, double prob, List<Class<?>> specialElements) {
		super(grid);
		this.prob = prob;
		this.specialElements = specialElements;

	}

	public CandyGeneratorCell(Grid grid){
		super(grid);
		prob = 0;
		specialElements = new ArrayList<>();
	}



	@Override
	public boolean isMovable(){
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}


	public Element getContent() {
		int i = (int)(Math.random() * CandyColor.values().length);
		if(prob >= (int) Math.random()*100 + 1){
			int j = (int)(Math.random()*specialElements.size());
			try {
				return (Element) specialElements.get(j).newInstance();
			}catch(IllegalAccessException | InstantiationException e) { //ver si es lo mejor
			System.out.println("ERROR AL INICIAR");
			return null;
		}
		}else return new Candy(CandyColor.values()[i]);
	}

	@Override
	public Element getAndClearContent() {
		return getContent();
	}

	@Override
	public boolean fallUpperContent() {
		throw new IllegalStateException();
	}
	
	@Override
	public void setContent(Element content) {
		throw new IllegalStateException();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}

}
