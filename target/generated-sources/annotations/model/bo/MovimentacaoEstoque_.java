package model.bo;

import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Funcionario;
import model.bo.ItemCompra;
import model.bo.ItemVenda;
import model.bo.Produto;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-04-11T18:51:37")
@StaticMetamodel(MovimentacaoEstoque.class)
public class MovimentacaoEstoque_ { 

    public static volatile SingularAttribute<MovimentacaoEstoque, Character> flagTipoMovimento;
    public static volatile SingularAttribute<MovimentacaoEstoque, ItemCompra> itemCompra;
    public static volatile SingularAttribute<MovimentacaoEstoque, String> observacaoMovimento;
    public static volatile SingularAttribute<MovimentacaoEstoque, ItemVenda> itemVenda;
    public static volatile SingularAttribute<MovimentacaoEstoque, Produto> produto;
    public static volatile SingularAttribute<MovimentacaoEstoque, DateTimeFormatter> dataHoraMovimento;
    public static volatile SingularAttribute<MovimentacaoEstoque, Integer> id;
    public static volatile SingularAttribute<MovimentacaoEstoque, Funcionario> funcionario;
    public static volatile SingularAttribute<MovimentacaoEstoque, Float> qtdMovimentada;
    public static volatile SingularAttribute<MovimentacaoEstoque, Character> status;

}