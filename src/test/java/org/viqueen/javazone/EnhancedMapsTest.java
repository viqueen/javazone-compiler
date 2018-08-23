package org.viqueen.javazone;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EnhancedMapsTest {

    /**
     * Exercise 1: introducing a javascript like dictionary definition
     * Map empty = {};
     *
     * Hints :
     * - we need to identify the syntax, LBRACE RBRACE
     * - and convert it to an equivalent statement : Collections.emptyMap()
     * - this statement is essentially a variable initialization expression
     * - have a look around {@code com.sun.tools.javac.parser.JavacParser} to see if anything sparks up ideas.
     * - ok fine, it's similar to an array initialization.
     * - so basically you need to modify the variable init method to take into account the "type" being initialized
     * - also {@code com.sun.tools.javac.tree.TreeMaker} is where you create equivalent statements
     */

    @Test
    public void testEmptyMapDefinition() {
        final Map empty  = {};
        assertThat(empty.size(), is(0));
    }

    /**
     * Exercise 2: introducing a single entry map with a String key value pair ... baby steps
     * Map singleton = {
     *     "key": "value"
     * }
     *
     * Hints:
     * - now we need to identify a key value entry syntax :  LBRACE
     *      STRINGLITERAL COLON STRINGLITERAL
     *   RBRACE
     * - you can use token.kind and peekToken to determine if you have a valid syntax, or else you should fail the
     * compilation with an "illegal" error statement
     * - you have a utility method "illegal" you can use for that matter.
     * - you may use {@code com.sun.tools.javac.util.Pair} to hold the key value literals you are parsing
     */
    @Test
    public void testSingletonMapDefinition () {
        final Map singleton = {
              "name": "Hasnae R"
        };
        assertThat(singleton.size(), is(1));
    }

    /**
     * Exercise 3: introducing a multi entry map with String key value pairs
     * Map data = {
     *     "keyOne": "valueOne",
     *     "keyTwo": "valueTwo"
     * }
     *
     * Hints:
     * - now we need to identify the following syntax: LBRACE
     *          STRINGLITERAL COLON STRINGLITERAL
     *          COMMA ? (optional)
     *      RBRACE
     *
     * - we need to modify our map entry parsing to consume entries as long as we did not hit a right brace
     * - in the TreeMaker we now have to construct a new Map, for simplicity purposes let us use a HashMap impl.
     * - for every entry we parsed we should invoke the HashMap#put method
     * - in a way we need to replicate the following java syntax :
     *      Map data = new HashMap()
     *      for (Pair entry : entries) {
     *          data.put(entry.first, entry.second);
     *      }
     *
     *      OR
     *
     *      Map data = new HashMap() {
     *          {
     *              this.put(entry.first, entry.second);
     *          }
     *      }
     * - let's attempt the second syntax as it is essentially just one statement
     * - have a look around AnonymousClassDef
     * - if you feel like a good citizen, let's ensure our new map syntax is actually immutable
     *     java.util.Collections.unmodifiableMap(theNewMap)
     */
    @Test
    public void testMultiEntryMapDefinition () {
        final Map data = {
                "name": "Hasnae R.",
                "age": "mind your own business FFS",
                "city": "Sydney"
        };
        assertThat(data.size(), is(3));
    }

}
