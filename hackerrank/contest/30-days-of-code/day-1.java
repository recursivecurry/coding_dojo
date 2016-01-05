import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        String[] outputString = new String[]{
                "Primitive : double",
                "Primitive : char",
                "Primitive : boolean",
                "Primitive : int",
                "Referenced : String",
                "Primitive : boolean",
                "Primitive : double",
                "Primitive : char",
                "Referenced : String"
        };
        Arrays.asList(outputString).stream().forEach(s -> {System.out.println(s);});
    }
}
