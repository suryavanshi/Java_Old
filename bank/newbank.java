/*contains main method and calls interest calculation method 
when the program is run and then calls the main gui frame of the bank*/

package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.*;
import java.lang.Object;

public class newbank
{
public static void main(String args[])
{
interest it=new interest();
try
{
it.calinterest();
it.loaninterest();
}
catch(Exception intcal)
{
System.out.println("from main"+intcal);
}
try
{
mainframe ent=new mainframe();
ent.mainframe2();
}
catch(Exception fr)
{
System.out.println("frame"+fr);
}
}
}