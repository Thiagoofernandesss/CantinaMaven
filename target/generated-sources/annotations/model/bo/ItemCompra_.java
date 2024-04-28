package model.bo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Compra;
import model.bo.Produto;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-28T19:27:33")
@StaticMetamodel(ItemCompra.class)
public class ItemCompra_ { 

    public static volatile SingularAttribute<ItemCompra, Float> qtdProduto;
    public static volatile SingularAttribute<ItemCompra, Compra> compra;
    public static volatile SingularAttribute<ItemCompra, Produto> produto;
    public static volatile SingularAttribute<ItemCompra, Integer> id;
    public static volatile SingularAttribute<ItemCompra, Float> valorUnitario;
    public static volatile SingularAttribute<ItemCompra, Character> status;

}