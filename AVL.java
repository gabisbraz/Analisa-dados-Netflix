public class AVL {
    private NoAVL raiz;
    private int nElem; // N�mero de elementos (n�s) na �rvore

    public AVL() {
        raiz = null;
        nElem = 0;
    }

    public AVL(ProgramaNetFlix programa) {
        raiz = new NoAVL(programa, 0, null, null, null);
        nElem = 1;
    }

    public NoAVL getNoRaiz() {
        return raiz;
    }

    private void setNoRaiz(NoAVL novoNoRaiz) {
        raiz = novoNoRaiz;
    }

    public int getNElem() {
        return nElem;
    }

    public void setNElem(int nElem) {
        this.nElem = nElem;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public NoAVL searchAVL(ProgramaNetFlix programa) {
        return searchNoAVL(raiz, programa);
    }

    private NoAVL searchNoAVL(NoAVL noAtual, ProgramaNetFlix programa) {
        if (noAtual == null) {
            return null;
        }

        int compareResult = programa.getId().compareTo(noAtual.getElement().getId());

        if (compareResult == 0) {
            return noAtual;
        } else if (compareResult < 0) {
            return searchNoAVL(noAtual.getLeft(), programa);
        } else {
            return searchNoAVL(noAtual.getRight(), programa);
        }
    }

    public void insereAVL(ProgramaNetFlix programa) {
        setNoRaiz(insereNoAVL(raiz, programa));
        setNElem(getNElem() + 1);
    }

    private NoAVL insereNoAVL(NoAVL noAtual, ProgramaNetFlix programa) {
        if (noAtual == null) {
            return new NoAVL(programa, 0, null, null, null);
        }

        int compareResult = programa.getId().compareTo(noAtual.getElement().getId());

        if (compareResult < 0) {
            noAtual.setLeft(insereNoAVL(noAtual.getLeft(), programa));
        } else if (compareResult > 0) {
            noAtual.setRight(insereNoAVL(noAtual.getRight(), programa));
        } else {
            // Handle duplicate IDs if needed
        }

        noAtual.setFb(calcularFatorBalanceamento(noAtual));
        return balancear(noAtual);
    }

    private int calcularFatorBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }

        int alturaEsquerda = calcularAlturaAVL(no.getLeft());
        int alturaDireita = calcularAlturaAVL(no.getRight());

        return alturaEsquerda - alturaDireita;
    }

    private int calcularAlturaAVL(NoAVL no) {
        if (no == null) {
            return 0;
        }

        int alturaEsquerda = calcularAlturaAVL(no.getLeft());
        int alturaDireita = calcularAlturaAVL(no.getRight());

        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    private NoAVL balancear(NoAVL no) {
        int fatorBalanceamento = no.getFb();

        if (fatorBalanceamento < -1) {
            if (calcularFatorBalanceamento(no.getRight()) > 0) {
                no.setRight(rotacaoDireita(no.getRight()));
            }
            return rotacaoEsquerda(no);
        } else if (fatorBalanceamento > 1) {
            if (calcularFatorBalanceamento(no.getLeft()) < 0) {
                no.setLeft(rotacaoEsquerda(no.getLeft()));
            }
            return rotacaoDireita(no);
        }

        return no;
    }

    private NoAVL rotacaoEsquerda(NoAVL desbA) {
        NoAVL aux = desbA.getRight();
        desbA.setRight(aux.getLeft());
        aux.setLeft(desbA);
        aux.setFb(0);
        desbA.setFb(0);
        return aux;
    }

    private NoAVL rotacaoDireita(NoAVL desbA) {
        NoAVL aux = desbA.getLeft();
        desbA.setLeft(aux.getRight());
        aux.setRight(desbA);
        aux.setFb(0);
        desbA.setFb(0);
        return aux;
    }
}
