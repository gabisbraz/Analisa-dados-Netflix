public class NoAVL {
   protected ProgramaNetFlix programa; // Elemento a ser armazenado
   protected NoAVL parent; // Ponteiro para o parent do n�
   protected NoAVL left;
   protected NoAVL right;
   private int fb; // Fator de balanceamento

   public NoAVL() {
      this(null, 0, null, null, null);
   }

   public NoAVL(ProgramaNetFlix programa){
      this(programa, null);
   }

   public NoAVL(ProgramaNetFlix programa, NoAVL parent) {
      this.fb = 0;
      this.programa = programa;
      this.parent = parent;
      this.left = null;
      this.right = null;
   }

   public NoAVL(ProgramaNetFlix programa, int fb, NoAVL parent, NoAVL left, NoAVL right) {
      setElement(programa);
      this.fb = fb;
      setParent(parent);
      setRight(right);
      setLeft(left);
   }

   public int getFb() {
      return fb;
   }
   public void setFb(int fb) {
      this.fb = fb;
  }

  public ProgramaNetFlix getElement() {
     return programa;
   }
   public void setElement(ProgramaNetFlix programa) {
      this.programa = programa;
   }

   public NoAVL getParent() {
      return parent;
   }
   public void setParent(NoAVL parent) {
      this.parent = parent;
   }

   public NoAVL getRight() {
      return right;
   }
   public void setRight(NoAVL right) {
      this.right = right;


      if (this.right != null) {
          this.right.setParent(this);
      }
  }


   public NoAVL getLeft() {
      return left;
   }
   public void setLeft(NoAVL left) {
      this.left = left;


      if (this.left != null) {
          this.left.setParent(this);
      }
  }


}
