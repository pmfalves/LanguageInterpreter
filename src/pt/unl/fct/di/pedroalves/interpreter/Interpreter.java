package pt.unl.fct.di.pedroalves.interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import pt.unl.fct.di.pedroalves.ast.ASTNode;
import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.parser.ParseException;
import pt.unl.fct.di.pedroalves.parser.Parser;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * The interpreter takes a source file and evaluates the code.
 * @author pedroalves
 *
 */
public class Interpreter {

	public static void main(String[] args) throws FileNotFoundException {

		if (args.length != 1) {
			System.err.println("Use: java pt.fct.unl.di.pedroalves.interpreter.Interpreter <source_file>");
			System.exit(0);
		}

		final String filename = args[0];

		Interpreter.run(filename);
	}

	/**
	 * Interprets a given file
	 * @param fn
	 * @throws FileNotFoundException
	 */
	public static void run(String fn) throws FileNotFoundException {

		final File source = new File(fn);
		final FileInputStream fis = new FileInputStream(source);

		new Parser(fis);
		ASTNode astree;

		try {
			astree = Parser.Start();
			astree.typecheck(new TypeEnvironment());
			final Value value = astree.eval(new Environment());
			if (value != null) {
				System.out.println(value);
			}
		} catch (TypeError e) {
			System.err.println("Type Error: " + e.getMessage());
		} catch (UnknownIdentifierException e) {
			System.err.println("Unknown Identifier: " + e.getMessage());
		} catch (ParseException e) {
			System.err.println("Syntax Error: " + e.getMessage());
			Parser.ReInit(System.in);
		}

	}
}
