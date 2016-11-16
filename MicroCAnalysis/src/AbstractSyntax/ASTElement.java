package AbstractSyntax;

import datastructure.Declaration.Declaration;
import datastructure.Statement.Statement;

public class ASTElement {

	public static ASTElement convertTextToASTElement(String text) {
			ASTElement element = new ASTElement();
			if (text.substring(1, 5).equals("decl")) {
				element = Declaration.convertTextToASTElement(text);
			} else if (text.substring(1, 5).equals("stmt")) {
				element = Statement.convertTextToASTElement(text);
			}
			return element;
		}

}
