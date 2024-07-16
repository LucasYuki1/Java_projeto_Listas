public class Produtos implements Comparable<Produtos>{
    private String nomeProduto;
    private double preco;
    
    public Produtos(String nomeProduto, double preco){
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    public String getNomeProduto(){
        return nomeProduto;
    }
    public double getPreco(){
        return preco;
    }

    @Override
    public String toString(){
        return String.format("%s - %.2f R$", nomeProduto, preco);
    }
    @Override
    public int compareTo(Produtos outroProduto){
        return this.getNomeProduto().compareTo(outroProduto.getNomeProduto());
    }
    
}
