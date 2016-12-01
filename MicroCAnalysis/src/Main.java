import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import AbstractSyntax.ASTAnalysis;
import AbstractSyntax.ASTNode;
import AbstractSyntax.FlowNode;
import algorithm.worklist.MFP;
import algorithm.worklist.Worklist;
import monotone.MonotoneFramework;
import monotone.ReachingDefinitionAnalysis;
import monotone.SignDetectionAnalysis;
import parser.MicroCLexer;
import parser.MicroCParser;

public class Main {
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Choose analysis you want done (RD or DS): ");
		String analysis = br.readLine();
		System.out.println("Choose the file that the " + analysis + " analysis should run on: ");
		Files.walk(Paths.get("example/")).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				System.out.print(filePath.getFileName() + "\t");
			}
		});
		System.out.println();
		System.out.print("File: ");
		String file = br.readLine();
		file = "example/" + file;
		InputStream input = new FileInputStream(file);
		CharStream cs = new ANTLRInputStream(input);

		MicroCLexer lex = new MicroCLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		MicroCParser parser = new MicroCParser(tokens);

		try {
			MicroCParser.ProgramContext parserResult = parser.program();
			// System.out.println(parserResult);
			ASTAnalysis analyais = new ASTAnalysis();
			ASTNode ast = analyais.toAST(parserResult.children, parser);
			// a.showAST(ast, 0);
			ast.getStmtAndDecl();
			analyais.showAST(ast, 0);
			
			FlowNode node = analyais.toFlowGraph(ast);
			analyais.showFlow(node);
			System.out.println(analyais.flowGraph);
			
			if (analysis.equalsIgnoreCase("DS")) {
				System.out.println();
				System.out.println("Detection of Signs analysis result:");
				Worklist ds = new SignDetectionAnalysis(new MonotoneFramework(node, node.toList(), '0'));
				MFP result = ds.worklistAlgorithm();

				System.out.print("  Signs Analysis Entry:");
				System.out.print("\t\t");
				System.out.print("Signs Analysi Exit:");
				System.out.println();
				for (int i = 0; i < result.getCircles().length; i++) {
					if(result.getCircle(i+1) != null && result.getCircle(i+1).size() >0) {
						System.out.print(i + 1 + ": ");
						ds.printList(result.getCircle(i+1));
						if(i+1 < 5) {
							System.out.print("\t");
						}
						ds.printList(result.getBullet(i+1));
						System.out.println();
					}
				}
			}
			if (analysis.equals("RD")) {
				System.out.println();
				System.out.println("Reaching defintion result:");
				Worklist rd = new ReachingDefinitionAnalysis(new MonotoneFramework(node, node.toList()));
				MFP r = rd.worklistAlgorithm();

				System.out.print("   Reaching Definition Entry:");
				System.out.print("\t\t");
				System.out.print("Reaching Definition Exit:");
				System.out.println();
				for (int i = 0; i < r.getCircles().length; i++) {
					if(r.getBullet(i+1) != null && r.getBullet(i+1).size() !=0 ) {
						System.out.print(i + 1 + ": ");
						rd.printList(r.getCircle(i + 1));
						System.out.print("\t\t");
						rd.printList(r.getBullet(i + 1));
						System.out.println();
					}
				}
			}
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
