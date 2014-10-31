/*
 * 
	Author: SAI SRI MOURYA GUDLADONA
	Description: GIVEN AN INORDER AND POST-ORDER TARVERSALS OF A TREE, A TREE CAN BE CONSTRUCTED
				 file.txt IS INPUT FILE. LINE 1 IS INORDER, LINE 2 IS POST ORDER.  
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Node {

	public static Node ROOT_NODE;
	public static Node NULL_NODE;
	
	int data; 
	Node Left_child; 
	Node Right_child; 
	
	//-------------- THIS FUNCTION SEARCHES THE TREE FOR A NODE TO BE INSERTED INTO THE TREE-----------//
	public static Node search(Node temp_node, int node_data){
		
		 
		Node result; 
		if(temp_node!=null){
				
				if(temp_node.data==node_data){				
						return temp_node; 
				}
				else if((result=search(temp_node.Left_child,node_data))!=null){				
					return result;
				}				
				else
					{
						if((result=search(temp_node.Right_child,node_data))!=null)					
							return result;			
					}
					
		}
		return null;
		
	}
	
	//------------------------- THIS FUNCTION IS USED TO ADD LEFT AND RIGHT NODES INTO THE TREE------------// 
	//------------------------- FOR EVERY ITERATION OF buildTree METHOD, LEFT & RIGHT NODES TO ------------//
	//------------------------- "root" ARE CREATED IF THEY ARE NOT CREATED SO FAR 			   ------------//
	
	public static void constructTree(int left, int right, int root){
		
		Node final_node; 
		final_node=search(ROOT_NODE, root);
		
		if(final_node!=null)
		{
	 	
			if(left!=-10)
				{
				Node left_node=new Node();
				final_node.Left_child=left_node;
				left_node.data=left;
				System.out.println(" NODE CREATED AS "+left_node.data +" LEFT OF "+ final_node.data);
				}
			if(right!=-10)
				{
				Node right_node=new Node();
				final_node.Right_child=right_node;
				right_node.data=right;
				System.out.println(" NODE CREATED AS "+right_node.data+" RIGHT OF "+ final_node.data);
				}
		}
	}
	
	public static void buildTree(ArrayList<Integer> InOrder, ArrayList<Integer> PostOrder){
		
		if(InOrder.size()<=0)
			 return;
		
		
		int Root_Pos= PostOrder.size()-1; 
		int Root= PostOrder.get(Root_Pos); 
		
		System.out.println("ROOT "+ Root );
		
		int LST_Inindex_end=InOrder.indexOf(Root)-1; 
		int RST_Inindex_start=InOrder.indexOf(Root)+1;
		 
		 ArrayList<Integer> LST_InOrder = new ArrayList<Integer>(); 
		 ArrayList<Integer> RST_InOrder = new ArrayList<Integer>();
		 ArrayList<Integer> LST_PostOrder = new ArrayList<Integer>(); 
		 ArrayList<Integer> RST_PostOrder = new ArrayList<Integer>();
		
					
		int i=0; 
		//---------------- LEFT SUB-TREE's INORDER AND POST ORDER ARRAYS ARE CALCULATED RECURSIVELY --------------//
		for(i=0;i<=LST_Inindex_end;i++){
			LST_InOrder.add(InOrder.get(i));
			LST_PostOrder.add(PostOrder.get(i));
		}
		//---------------- RIGHT SUB-TREE's INORDER AND POST ORDER ARRAYS ARE CALCULATED RECURSIVELY --------------//
		for(i=RST_Inindex_start;i<=(PostOrder.size()-1);i++){
			RST_InOrder.add(InOrder.get(i));
			RST_PostOrder.add(PostOrder.get(i-1));
		}
		
		//-------- A NODE POINT TO A NULL NODE(-10) IF IT HAS NO CHILDREN -----------//
		//-------- ALL LEAF NODES POINT TO NULL NODE 		         -----------//
		
		int RIGHT=-10, LEFT=-10;
		
		//---------- LEFT & RIGHT VARIABLES DENOTE DATA IN LEFT NODE AND DATA IN RIGHT NODES RESPECTIVELY ---------//
		if(LST_PostOrder.size()!=0){
			LEFT=LST_PostOrder.get(LST_PostOrder.size()-1);
		}
		else{
			LEFT=-10;
		}
		if(RST_PostOrder.size()!=0){
			RIGHT=RST_PostOrder.get(RST_PostOrder.size()-1);
		}
		else{
			RIGHT=-10;
		}
		
		constructTree(LEFT, RIGHT, Root);
		buildTree(LST_InOrder, LST_PostOrder); // CONSTRUCT LEFT SUB TREE
	    buildTree(RST_InOrder, RST_PostOrder); // CONSTRUCT RIGHT SUB TREE
		
	}
	
	// THIS FUNCTION IS TO TEST IF THE TREE IS BULIT PROPERLY OR NOT.
	// GIVES AN ARRAY LIST OF INORDER TRAVERSAL, I COMAPRED THAT INPUT AND THIS METHOD's OUTPUT TO TEST FOR CORRECTNESS
	
	public static void inorder(Node root_node){
		
		if(root_node!=null)
			{
			inorder(root_node.Left_child);
			System.out.print(" "+root_node.data);
			inorder(root_node.Right_child);
			}
	}
public static void main(String args[]){
	
	ArrayList<Integer> Inorder = new ArrayList<Integer>();
	ArrayList<Integer> Postorder = new ArrayList<Integer>();
	

	
    try {
		BufferedReader br = new BufferedReader(new FileReader("file.txt"));
		
		String str_inorder=br.readLine();
		String str_postorder=br.readLine();
		
		String[] token_in = str_inorder.split(","); 
		String[] token_post=str_postorder.split(",");
		
		int k=0; 
		
		for(k=0;k<token_in.length;k++){
				Inorder.add(Integer.parseInt(token_in[k]));
				Postorder.add(Integer.parseInt(token_post[k]));
		}
				
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	
	//ROOT NODE FOR TREE IS FIXED. 
	Node.ROOT_NODE= new Node();
	ROOT_NODE.data= Postorder.get(Postorder.size()-1);
	
	// ALL LEAF NODES POINTS TO THIS "NULL_NODE"
	Node.NULL_NODE=new Node();
	NULL_NODE.data=-10; 
	Node.buildTree(Inorder, Postorder); 
		
	System.out.println("INORDER TRAVERSAL OF CREATED TREE IS : ");
	inorder(ROOT_NODE);
	
	
}
}
