package com.rlsp.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rlsp.ecommerce.listener.GenericoListener;
import com.rlsp.ecommerce.listener.GerarNotaFiscalListener;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @Entity = tabela do DB (Base do JPA/Hibernate)
 * @Id = chave primaria (Base do JPA/Hibernate)
 * @Getter e @Setter = do project LOMBOK (Getters, setters, etc)
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({ GenericoListener.class })
@Entity
@Table(name= "produto")
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;
    /**
     * @Column (Detalhes)      
     *  - "updatable = false" : para NAO ser MODIFICADO
     *  - "insertable = false" : NAO SERA valorado na criacao do objeto
     */
    @Column(name="data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name="data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;
    
    
    @ManyToMany
    @JoinTable(name="produtos_categorias", 
    			joinColumns = @JoinColumn(name="produto_id"), //Coluna da tabela "produtos_categorias" (tabela join) que referencia o id da tabela Produto
    			inverseJoinColumns = @JoinColumn (name ="categoria_id")) //Coluna da tabela "produtos_categorias" (tabela join) que referencia o id da tabela Categoria    			
    private List<Categoria> categorias;
    
    @OneToOne(mappedBy = "produto")
    private Estoque estoque;
    

	
	
}
