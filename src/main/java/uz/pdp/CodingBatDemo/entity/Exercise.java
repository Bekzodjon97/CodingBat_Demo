package uz.pdp.CodingBatDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private  Chapter chapter;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private boolean hasStar=false;

    @OneToOne
    private HintSolution hintSolution;



}
