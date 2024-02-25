class A{  
    protected void msg(){System.out.println("Hello java");}  
    }  
      
    public class index extends A{  
    public void msg(){System.out.println("Hello java");} 
     public static void main(String args[]){  
       index obj=new index();  
       obj.msg();  
       }  
    }