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
			ASTAnalysis a = new ASTAnalysis();
			ASTNode ast = a.toAST(parserResult.children, parser);
			// a.showAST(ast, 0);
			ast.getStmtAndDecl();
//			Node node = a.toFlowGraph(ast, 0);
//			a.showAST(ast, 0);
			
//			if (analysis.equals("RD")) {
//				System.out.println();
//				System.out.println("Reaching defintion result:");
//				Worklist rd = new analysis.worklist.ReachingDefinition(new MonotoneFramework(node, node.toList()));
//				MFP r = rd.worklistAlgorithm();
//
//				System.out.print("   Circle:");
//				System.out.print("\t\t\t");
//				System.out.print("Bullet:");
//				System.out.println();
//				for (int i = 0; i < r.getCircles().length; i++) {
//					System.out.print(i + 1 + ": ");
//					rd.printList(r.getCircle(i + 1));
//					rd.printList(r.getBullet(i + 1));
//					System.out.println();
//				}
//			} else if (analysis.equalsIgnoreCase("DS")) {
//				System.out.println();
//				System.out.println("Detection of Signs analysis result:");
//				Worklist ds = new SignsAnalysisDefinition(new MonotoneFramework(node, node.toList(), '0'));
//				MFP result = ds.worklistAlgorithm();
//
//				System.out.print("   Circle:");
//				System.out.print("\t\t\t");
//				System.out.print("Bullet:");
//				System.out.println();
//				for (int i = 0; i < result.getCircles().length; i++) {
//					System.out.print(i + 1 + ": ");
//					ds.printList(result.getCircle(i + 1));
//					ds.printList(result.getBullet(i + 1));
//					System.out.println();
//				}
//			}

		} catch (RecognitionException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
