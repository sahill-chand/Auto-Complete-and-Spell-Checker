import java.lang.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Main{
    public static void main(String[] args) throws FileNotFoundException {
      /*
      File file=new File("./text.txt");
      Scanner read=new Scanner(file);
      while(read.hasNext()){
        
      }
      */
      Scanner sc=new Scanner(System.in);
      Trie head=new Trie();
      for(int i=0;i<5;i++){
        String s=sc.nextLine();
        //before creating object convert string to lowercase.
        s=s.toLowerCase();
        head.insert(s);
      }
      head.match("pre");
      System.out.println("");
      //head.match("pr");
  }
}
class Trie{
  //put exception where needed.
  Trie[] trie=null;
  //isend will be true if some string had ended here.
  boolean isend;
  //level will be storing till what index the word is present.
  static int level=-1;
  //frequency stores the frequency of current suffix.
  int frequency;
  public Trie(){
    this.trie=new Trie[26];
    this.isend=false;
    this.frequency=0;
  }
  //created method overloading so that we need not to have index parameter at the main function.
  public void insert(String s){
    insert(s,0);
  }
  public void insert(String s,int index){
    if(index<s.length()){
      if(this.trie[s.charAt(index)-'a']==null){
        this.trie[s.charAt(index)-'a']=new Trie();
        this.trie[s.charAt(index)-'a'].frequency++;
        this.trie[s.charAt(index)-'a'].insert(s,index+1);
      }
      else{
        this.trie[s.charAt(index)-'a'].frequency++;
        this.trie[s.charAt(index)-'a'].insert(s,index+1);
      }
    }
    else
      this.isend=true;
  }
  public boolean search(String s){
    if(this.trie[s.charAt(0)-'a']==null)
      return false;
    level++;
    return this.trie[s.charAt(0)-'a'].search(s,1);
  }
  public boolean search(String s,int index){
    if(index==s.length()){
      if(this.isend==true)
        return true;
      else
        return false;
    }
    if(this.trie[s.charAt(index)-'a']==null)
      return false;
    level++;
    return this.trie[s.charAt(index)-'a'].search(s,index+1);
  }
  public boolean prefix(String s,int index){
    if(index==s.length())
      return true;
    if(this.trie[s.charAt(index)-'a']==null)
      return false;
    else
      return this.trie[s.charAt(index)-'a'].prefix(s,index+1);
  }
  public void print(Trie x){
    if(x.isend)
      return ;
    for(int i=0;i<26;i++){
      if(x.trie[i]!=null){
        System.out.print((char)('a'+i)+" ");
        x=x.trie[i];
        break;
      }
    }
    print(x);
  }
  public void match(String s){
    this.level=-1;
    search(s);
    match(s,0);
  }
  public void match(String s,int index){
    if(index<=level){
      System.out.print(s.charAt(index)+" ");
      trie[s.charAt(index)-'a'].match(s,index+1);
    }
    else{
      int max=-1;
      int Index=-1;
      for(int i=0;i<26;i++){
        if(this.trie[i]!=null){
          if(trie[i].frequency>max){
            max=trie[i].frequency;
            Index=i;
          }
        }
      }
      if(Index==-1)
        return;
      System.out.print((char)('a'+Index)+" ");
      this.trie[Index].match(s,index+1);
    }
  }
}