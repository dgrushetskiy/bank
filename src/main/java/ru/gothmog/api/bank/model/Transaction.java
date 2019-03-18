package ru.gothmog.api.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "transaction", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "business")
    private String business;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "account")
    private Integer account;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "transaction")
    private User user;
}
