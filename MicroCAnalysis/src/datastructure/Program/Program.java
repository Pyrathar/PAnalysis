package datastructure.Program;

import datastructure.Declaration.Declaration;
import datastructure.Statement.Statement;

public class Program extends Statement {
	
	private Declaration declar;
	
	private Statement statement;
	
	public Program(Declaration declar, Statement statement){
		this.declar = declar;
		this.statement = statement;
	}

}