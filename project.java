import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class Student{
    int roll;
    String name;
    String course;
    float marks;
    public void display(){
        System.out.println("-----------");
       System.out.println("roll number:"+this.roll);
       System.out.println("name:"+this.name);
       System.out.println("course:"+this.course);
       System.out.println("marks:"+this.marks);
       System.out.println("------------");
    }
}
public class project {
   public static void savetoCSV(ArrayList<Student>st){
    try{
     FileWriter w=new FileWriter("student.csv");
     for(Student s:st){
     w.write(s.roll+","+s.name+","+s.course +","+s.marks+"\n");
    }
    w.close();
    }
   catch(IOException e){
       System.out.println("ERROR");
   }
}
public static void fromcsv(ArrayList<Student>st){
    try{
        BufferedReader r=new BufferedReader(new FileReader("student.csv"));
        String line;
        while((line=r.readLine())!=null){
        String[] part=line.split(",");
        if(part.length==4){
            Student s=new Student();
            s.roll=Integer.parseInt(part[0]);
            s.name=part[1];
            s.course=part[2];
            s.marks=Float.parseFloat(part[3]);
            st.add(s);
        }
        }
        r.close();
        System.out.println("DATA LOADED");
    }
    catch(IOException e){
        System.out.println("No existing data found. Starting fresh");
    }
}
public static void main(String args[]){
       Scanner sc=new Scanner(System.in);//should be outside loop to prevent wastage of memory
       ArrayList<Student>student=new ArrayList<>();
       fromcsv(student);
       while(true){
        System.out.println("------------");
        System.out.println("1.ADD STUDENTS");
        System.out.println("2.DISPLAY All STUDENTS");
        System.out.println("3.UPDATE STUDENTS");
        System.out.println("4.DELETE STUDENTS");
        System.out.println("5.EXIT");
        System.out.println("6.SORT BY ROLL.NO");
        System.out.println("7.SORT BY NAME");
        System.out.println("8.SORT BY MARKS");
        System.out.println("9:DETAILS BASED ON COURSE");
        System.out.println("-----------");
        System.out.print("enter ur choice:");
        int n=sc.nextInt();
        switch(n){
        case 1:
            Student s1=new Student();
            while(true){
            System.out.println("enter roll:");
            int nroll=sc.nextInt(); //s1 is object of class student
            boolean found=false;
            for(Student stu1:student){
                if(nroll==stu1.roll){
                    found=true;
                    break;
                }
            }
            if(found) {
                System.out.print("roll number already exist");
                System.out.println("enter other roll number");
                }
            else{
                s1.roll=nroll;
                break;
                }
            }
            
            sc.nextLine();//Consume leftover newline if we remove this it directly goes to courses skips name bez When you use sc.nextInt() or sc.nextFloat() before sc.nextLine(), there's a hidden newline (\n) left in the input buffer.That leftover newline is immediately read by nextLine(), so it seems like it's skipping your name input.
            System.out.println("enter name:");
            s1.name=sc.nextLine();
            System.out.println("enter course:");
            s1.course=sc.nextLine();
            while(true){
            System.out.println("enter marks(0-100):");
           Float mark=sc.nextFloat();
           if(mark>100 || mark<0){
            System.out.println("PLEASE ENTER VALID MARKS");
           }
           else{
           s1.marks=mark;
           break;
           }
        }
            student.add(s1);
            savetoCSV(student);
            System.out.println("ADDED TO CSV FILE");
            break;
        case 2:
          if(student.isEmpty()){
          System.out.println("SORRY NO INFORMATION IS THERE");
          }
          else{
            for(Student stu:student){
                stu.display();
            }
            }
          break;
       case 3:
       System.out.print("enter updating roll number: ");
       int uproll=sc.nextInt();
       boolean info=false;
       for(Student s2:student){
        if(s2.roll==uproll){
       System.out.println("1.roll number");
       System.out.println("2.name");
       System.out.println("3.course");
       System.out.println("4.marks");
       int m=sc.nextInt();
       switch(m){
        case 1:
        while (true) {
        System.out.print("enter new roll number: ");
        int newroll = sc.nextInt(); // store in a temporary variable
        boolean found1 = false;

        for (Student stu1 : student) {
            if (newroll== stu1.roll) {
                found1 = true;
                break;
            }
        }

        if (found1) {
            System.out.println("roll number already exists. enter another roll number:");
        } else {
            s2.roll = newroll; // assign only if it's not duplicate
            break;
        }
    }

    sc.nextLine(); 
     System.out.println("roll number is updated");
    break;
        case 2:
        sc.nextLine();
        System.out.print("enter new name:");
        s2.name=sc.nextLine();
        System.out.println("name is updated");
        break;
        case 3:
        sc.nextLine();
        System.out.print("enter new course:");
        s2.course=sc.nextLine();
        System.out.println("course is updated");
        break;
        case 4:
         while(true){
            System.out.println("enter marks(0-100):");
           Float mark=sc.nextFloat();
           if(mark>100 || mark<0){
            System.out.println("PLEASE ENTER VALID MARKS");
           }
           else{
           s2.marks=mark;
           break;
           }
        }
        break;
        default:
        System.out.println("enter valid choice");
       }
       info=true;
       break;//it is not used again n again it will ask abt updation it will not come out of update
    }
}
   if(!info){
        System.out.println("No students are There with this Roll.NO");
    }
     savetoCSV(student);
     System.out.println("UPDATED TO CSV FILE");
    break;
       case 4:
       System.out.println("enter roll number u wanna delete:");
       int dlroll=sc.nextInt();
       boolean info1=false;
       for(int i=0;i<student.size();i++){
        if(student.get(i).roll==dlroll){
            student.remove(i);
            info1=true;
            System.out.println("Student deleted");
            System.out.println("DELETED FROM CSV FILE");
            break;
        }
    }
    if(!info1){
        System.out.println("No students are There with this Roll.NO");
    }
     savetoCSV(student);
     
       break;
       case 5:
       savetoCSV(student);//even after exiting info will be stored in file
       return; // exit
       case 6:
       if(student.isEmpty()){
        System.out.println("NO STUDENTS ARE THERE");
       }
       else{
        student.sort((a,b)-> Integer.compare(a.roll,b.roll));//collection sort builtin function for integer(lambda function)ascending order
        System.out.println("SORTED BY ROLL NO");
        System.out.println("--------");
        for(Student s:student){
            s.display();
        }
       }
       savetoCSV(student);
       break;
       case 7:
       if(student.isEmpty()){
        System.out.println("NO STUDENTS ARE THERE");
       }
       else{
        student.sort((a,b)->a.name.compareToIgnoreCase(b.name));//collection sort builtin function for characters(lambda function)
        System.out.println("SORTED BY NAME");
        System.out.println("--------");
        for(Student st:student){
            st.display();
        }
       }
       savetoCSV(student);
       break;
       case 8:
       if(student.isEmpty()){
        System.out.println("NO STUDENTS ARE THERE");
       }
       else{
        student.sort((a,b)->Float.compare(b.marks,a.marks));//collection sort builtin function for integer(lambda function) descending order
        System.out.println("STUDENTS");
        System.out.println("--------");
        for(Student st1:student){
            st1.display();
        }
       }
       savetoCSV(student);
       break;
       case 9:
        if(student.isEmpty()){
        System.out.println("NO STUDENTS ARE THERE");
        break;
       }
       sc.nextLine(); // This line is crucial
       System.out.println("enter course:");
       String dept=sc.nextLine();
       boolean found3 = false;
       for(Student st3:student){
        if(st3.course.equals(dept)){
            st3.display();
            found3=true;
        }
       }
      
       if (!found3) {
    System.out.println("No students found for the course: " + dept);
     }
     savetoCSV(student);
       break;
       default:
       System.out.println("INVALID CHOICE");
        }
    }
    }
}



