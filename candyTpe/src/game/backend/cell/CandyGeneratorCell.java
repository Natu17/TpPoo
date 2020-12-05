package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import sun.text.resources.pl.JavaTimeSupplementary_pl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class CandyGeneratorCell extends Cell {
	double prob;
	Supplier<Element> createSpecial;
	int count = 0;

	public CandyGeneratorCell(Grid grid, double prob, Supplier<Element> createSpecials) {
		super(grid);
		this.prob = prob;
		this.createSpecial = createSpecials;

	}

	public CandyGeneratorCell(Grid grid){
		super(grid);
		prob = 0;
		createSpecial = ()->null;
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
		if(prob >= (int) (Math.random()*100 + 1)){
			count++;
			System.out.println(count);
			return createSpecial.get();
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
