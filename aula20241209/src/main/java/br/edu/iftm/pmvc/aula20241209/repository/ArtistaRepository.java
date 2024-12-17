package br.edu.iftm.pmvc.aula20241209.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.pmvc.aula20241209.domain.Artista;

@Repository
public class ArtistaRepository {

    private JdbcTemplate conexao;

    public ArtistaRepository(JdbcTemplate conexao) {
        this.conexao = conexao;
    }

    public List<Artista> listar() {
        String sql = """
                select cod_artista as codigo,
                        nom_artista as nome
                    from tb_artista
                """;
        return conexao.query(sql, new BeanPropertyRowMapper<>(Artista.class));
    }

    public boolean inserir(Artista artista) {
        String sql = """
                insert into tb_artista (nom_artista)
                    values (?)
                """;
        return conexao.update(sql, artista.getNome()) > 0;
    }

    public List<Artista> buscarPorNomer(String nome) {
        String sql = """
                    select cod_artista as codigo,
                        nom_artista as nome
                    from tb_artista
                    where lower(nom_artista) like ?
                """;
        return conexao.query(sql,new BeanPropertyRowMapper<>(Artista.class),
        "%"+nome.toLowerCase()+"%");
    }

    public Artista buscarPorCod(Integer cod){
        String sql = """
                    select cod_artista as codigo,
                        nom_artista as nome
                    from tb_artista
                    where cod_artista = ?
                """;
                return conexao.queryForObject(sql,new BeanPropertyRowMapper<>(Artista.class), cod);
    }

    public int update(Artista artista) {
        // TODO Auto-generated method stub
        String sql = """
                UPDATE tb_artista
SET nom_artista = ?
WHERE cod_artista = ?
                """;

                return conexao.update(sql,artista.getNome(),artista.getCodigo());
    }
}
