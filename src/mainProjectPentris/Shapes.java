package mainProjectPentris;
import java.util.ArrayList;

public class Shapes
{
      
        
        
                //creating an arraylist with all pentominoes
                private ArrayList<ArrayList<int[][]>> pentominoList = new ArrayList<ArrayList<int[][]>>();
                private ArrayList<int[][]> pentrisShapes= new   ArrayList<int[][]>() ;
                private ArrayList<Integer> optiCoordsList= new ArrayList<Integer>();
                
                //creating pentomino arraylists with all possible rotations
               private ArrayList<int[][]> p = new ArrayList<int[][]>();
               private ArrayList<int[][]> x = new ArrayList<int[][]>();
               private ArrayList<int[][]> f = new ArrayList<int[][]>();
               private ArrayList<int[][]> v = new ArrayList<int[][]>();
               private ArrayList<int[][]> w = new ArrayList<int[][]>();
               private ArrayList<int[][]> y = new ArrayList<int[][]>();
               private ArrayList<int[][]> i = new ArrayList<int[][]>();
               private ArrayList<int[][]> t = new ArrayList<int[][]>();
               private ArrayList<int[][]> z = new ArrayList<int[][]>();
               private ArrayList<int[][]> u = new ArrayList<int[][]>();
               private ArrayList<int[][]> n = new ArrayList<int[][]>();
               private ArrayList<int[][]> l = new ArrayList<int[][]>();
                
	private int[][] p1 = { { 15, 15 }, { 15, 15 }, { 15, 0 } };
	private int[][] p2 = { { 15, 0 }, { 15, 15 }, { 15, 15 } };
	private int[][] p3 = { { 0, 15 }, { 15, 15 }, { 15, 15 } };
	private int[][] p4 = { { 15, 15 }, { 15, 15 }, { 0, 15 } };
	private int[][] p5 = { { 0, 15, 15 }, { 15, 15, 15 } };
	private int[][] p6 = { { 15, 15, 15 }, { 0, 15, 15 } };
	private int[][] p7 = { { 15, 15, 15 }, { 15, 15, 0 } };
	private int[][] p8 = { { 15, 15, 0 }, { 15, 15, 15 } };

                private int[][] x1 = { { 0, 25, 0 } , { 25, 25, 25 } , { 0, 25, 0 }};
                
                private int[][] f1 = { { 0, 30, 30 } , { 30, 30, 0 } , { 0, 30, 0 }};
                private int[][] f2 = { { 0, 30, 0 } , { 30, 30, 30 } , { 0, 0, 30 }};
                private int[][] f3 = { { 0, 30, 0 } , { 0, 30, 30 } , { 30, 30, 0 }};
                private int[][] f4 = { { 30, 0, 0 } , { 30, 30, 30 } , { 0, 30, 0 }};
                private int[][] f5 = { { 30, 30, 0 } , { 0, 30, 30 } , { 0, 30, 0 }};
                private int[][] f6 = { { 0, 0, 30 } , { 30, 30, 30 } , { 0, 30, 0 }};
                private int[][] f7 = { { 0, 30, 0 } , { 30, 30, 0 } , { 0, 30, 30 }};
                private int[][] f8 = { { 0, 30, 0 } , { 30, 30, 30 } , { 30, 0, 0 }};
                
                private int[][] v1 = { { 40, 0, 0 } , { 40, 0, 0 } , { 40, 40, 40 }};
                private int[][] v2 = { { 0, 0, 40 } , { 0, 0, 40 } , { 40, 40, 40 }};
                private int[][] v3 = { { 40, 40, 40 } , { 0, 0, 40 } , { 0, 0, 40 }};
                private int[][] v4 = { { 40, 40, 40 } , { 40, 0, 0 } , { 40, 0, 0 }};
                
                private int[][] w1 = { { 50, 0, 0 } , { 50, 50, 0 } , { 0, 50, 50 }};
                private int[][] w2 = { { 0, 50, 50 } , { 50, 50, 0 } , { 50, 0, 0 }};
                private int[][] w3 = { { 50, 50, 0 } , { 0, 50, 50 } , { 0, 0, 50 }};
                private int[][] w4 = { { 0, 0, 50 } , { 0, 50, 50 } , { 50, 50, 0 }};
                
                private int[][] y1 = { { 0, 45 } , { 45, 45 } , { 0, 45 }, { 0, 45 }};
                private int[][] y2 = { { 45, 0 } , { 45, 0 } , { 45, 45 }, { 45, 0 }};
                private int[][] y3 = { { 45, 0 } , { 45, 45 } , { 45, 0 }, { 45, 0 }};
                private int[][] y4 = { { 0, 45 } , { 0, 45 } , { 45, 45 }, { 0, 45 }};
                private int[][] y5 = { { 45, 45,  45, 45 } , { 0, 0, 45, 0 }};
                private int[][] y6 = { { 45, 45,  45, 45 } , { 0, 45, 0, 0 }};
                private int[][] y7 = { { 0, 45,  0, 0 } , { 45, 45, 45, 45 }};
                private int[][] y8 = { { 0, 0,  45, 0 } , { 45, 45, 45, 45 }};
                
                private int[][] i1 = { { 70 }, { 70 }, { 70 }, { 70 }, { 70 } };
                private int[][] i2 = { { 70, 70, 70, 70, 70 }};
                
                private int[][] t1 = { { 80, 80, 80 }, { 0, 80, 0 }, { 0, 80, 0 } };
                private int[][] t2 = { { 0, 0, 80 }, { 80, 80, 80 }, { 0, 0, 80 } };
                private int[][] t3 = { { 0, 80, 0 }, { 0, 80, 0 }, { 80, 80, 80 } };
                private int[][] t4 = { { 80, 0, 0 }, { 80, 80, 80 }, { 80, 0, 0 } };
                
                private int[][] z1 = { { 75, 75, 0 }, { 0, 75, 0 }, { 0, 75, 75 } };
                private int[][] z2 = { { 0, 0, 75 }, { 75, 75, 75 }, { 75, 0, 0 } };
                private int[][] z3 = { { 75, 0, 0 }, { 75, 75, 75 }, { 0, 0, 75 } };
                private int[][] z4 = { { 0, 75, 75 }, { 0, 75, 0 }, { 75, 75, 0 } };
                
                private int[][] u1 = { { 18, 0, 18 }, { 18, 18, 18 } };
                private int[][] u2 = { { 18, 18, 18 }, { 18, 0, 18 } };
                private int[][] u3 = { { 18, 18 }, { 18, 0 }, { 18, 18 } };
                private int[][] u4 = { { 18, 18 }, { 0, 18 }, { 18, 18 } };
                
                private int[][] n1 = { { 11, 11, 0, 0 }, { 0, 11, 11, 11 } };
                private int[][] n2 = { { 0, 11, 11, 11 }, { 11, 11, 0, 0 } };
                private int[][] n3 = { { 11, 11, 11, 0 }, { 0, 0, 11, 11 } };
                private int[][] n4 = { { 0, 0, 11, 11 }, { 11, 11, 11, 0 } };
                private int[][] n5 = { { 0, 11 }, { 11, 11 }, { 11, 0 }, { 11, 0 } };
                private int[][] n6 = { { 11, 0 }, { 11, 11 }, { 0, 11 }, { 0, 11 } };
                private int[][] n7 = { { 0, 11 }, { 0, 11 }, { 11, 11 }, { 11, 0 } };
                private int[][] n8 = { { 11, 0 }, { 11, 0 }, { 11, 11 }, { 0, 11 } };
                
                private int[][] l1 = { { 12, 12 }, { 12, 0 }, { 12, 0 }, { 12, 0 } };
                private int[][] l2 = { { 12, 12 }, { 0, 12 }, { 0, 12 }, { 0, 12 } };
                private int[][] l3 = { { 12, 0 }, { 12, 0 }, { 12, 0 }, { 12, 12 } };
                private int[][] l4 = { { 0, 12 }, { 0, 12 }, { 0, 12 }, { 12, 12 } };
                private int[][] l5 = { { 0, 0, 0, 12 }, { 12, 12, 12, 12 } };
                private int[][] l6 = { { 12, 12, 12, 12 }, { 0, 0, 0, 12 } };
                private int[][] l7 = { { 12, 0, 0, 0 }, { 12, 12, 12, 12 } };
                private int[][] l8 = { { 12, 12, 12, 12 }, { 12, 0, 0, 0 } };
				
                
                
                private ArrayList<int[][]> optiData= new ArrayList<int[][]>();
                
                //adding all rotations in rotation lists
                public Shapes(){
                p.add(p1);
                p.add(p2);
                p.add(p3);
                p.add(p4);
                p.add(p5);
                p.add(p6);
                p.add(p7);
                p.add(p8);
                
                x.add(x1);
                
                f.add(f1);
                f.add(f2);
                f.add(f3);
                f.add(f4);
                f.add(f5);
                f.add(f6);
                f.add(f7);
                f.add(f8);
                
                v.add(v1);
                v.add(v2);
                v.add(v3);
                v.add(v4);
                
                w.add(w1);
                w.add(w2);
                w.add(w3);
                w.add(w4);
                
                y.add(y1);
                y.add(y2);
                y.add(y3);
                y.add(y4);
                y.add(y5);
                y.add(y6);
                y.add(y7);
                y.add(y8);
                
                // change
                i.add(i1);
                i.add(i2);
                
                t.add(t1);
                t.add(t2);
                t.add(t3);
                t.add(t4);
                
                z.add(z1);
                z.add(z2);
                z.add(z3);
                z.add(z4);
                
                u.add(u1);
                u.add(u2);
                u.add(u3);
                u.add(u4);
                
                n.add(n1);
                n.add(n2);
                n.add(n3);
                n.add(n4);
                n.add(n5);
                n.add(n6);
                n.add(n7);
                n.add(n8);
                
                l.add(l1);
                l.add(l2);
                l.add(l3);
                l.add(l4);
                l.add(l5);
                l.add(l6);
                l.add(l7);
                l.add(l8);
                
                //adding rotation lists in pentomino list
                pentominoList.add(p);
                pentominoList.add(x);
                pentominoList.add(f);
                pentominoList.add(v);
                pentominoList.add(w);
                pentominoList.add(y);
                pentominoList.add(i);
                pentominoList.add(t);
                pentominoList.add(z);
                pentominoList.add(u);
                pentominoList.add(n);
                pentominoList.add(l);
                
                pentrisShapes.add(p1);
                pentrisShapes.add(p4);
                pentrisShapes.add(x1);
                pentrisShapes.add(f1);
                pentrisShapes.add(f5);
                pentrisShapes.add(v1);
                pentrisShapes.add(y1);
                pentrisShapes.add(y3);
                pentrisShapes.add(w1);
                pentrisShapes.add(i1);
                pentrisShapes.add(t1);
                pentrisShapes.add(z1);
                pentrisShapes.add(u1);
                pentrisShapes.add(n1);
                pentrisShapes.add(n2);
                pentrisShapes.add(l1);
                pentrisShapes.add(l2);
                //optidata
                optiData.add(v2);//kaguria beni to proto sto constructor
                optiData.add(v2);
                optiData.add(z2);
                optiData.add(f8);
                
                optiData.add(n5);
                optiData.add(p2);
                optiData.add(w3);
                optiData.add(t2);
                optiData.add(y2);
                optiData.add(i1);
                optiData.add(x1);
                optiData.add(u2);
                optiData.add(l1);
                
                
                optiCoordsList.add(77);
                optiCoordsList.add(3);
                optiCoordsList.add(4);
                optiCoordsList.add(0);
                optiCoordsList.add(3);
                optiCoordsList.add(77);
                optiCoordsList.add(77);
                optiCoordsList.add(3);
                optiCoordsList.add(0);
                optiCoordsList.add(77);
                optiCoordsList.add(77);
                optiCoordsList.add(0);
                }
                

       
        public  ArrayList<ArrayList<int[][]>> givePentominoData(){
        	return pentominoList;
        }
        
        public  ArrayList<int[][]> givePentrisData(){
        	return pentrisShapes;
        }
        public ArrayList<int[][]> giveOptiData(){
        	return optiData;
        	
        }
        public ArrayList<Integer> optiCoords(){
        	return optiCoordsList;
        }
        
}       
