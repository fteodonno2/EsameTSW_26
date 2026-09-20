package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.beans.Product;

public class ProductDAO {

    public static List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getBigDecimal("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                p.setImmagine(rs.getString("immagine"));
                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public static Product getById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getBigDecimal("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                p.setImmagine(rs.getString("immagine"));
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean create(Product product) {
        String sql = "INSERT INTO products (nome, descrizione, prezzo, categoria, immagine) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getNome());
            pstmt.setString(2, product.getDescrizione());
            pstmt.setBigDecimal(3, product.getPrezzo());
            pstmt.setString(5, product.getCategoria());
            pstmt.setString(6, product.getImmagine());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(Product product) {
        String sql = "UPDATE products SET nome = ?, descrizione = ?, prezzo = ?, categoria = ?, immagine = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getNome());
            pstmt.setString(2, product.getDescrizione());
            pstmt.setBigDecimal(3, product.getPrezzo());
            pstmt.setString(4, product.getCategoria());
            pstmt.setString(5, product.getImmagine());
            pstmt.setInt(6, product.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Product> search(String keyword) {
        List<Product> risultati = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE LOWER(nome) LIKE ? OR LOWER(descrizione) LIKE ? OR LOWER(categoria) LIKE ?";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String filtro = "%" + keyword.toLowerCase() + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);
            stmt.setString(3, filtro);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getBigDecimal("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                p.setImmagine(rs.getString("immagine"));
                risultati.add(p);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return risultati;
    }

    public List<Product> getBestseller() throws SQLException {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id ASC LIMIT 3";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getBigDecimal("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                p.setImmagine(rs.getString("immagine"));
                products.add(p);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public List<Product> getLatestProducts(int limit) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id DESC LIMIT ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setDescrizione(rs.getString("descrizione"));
                    p.setPrezzo(rs.getBigDecimal("prezzo"));
                    p.setCategoria(rs.getString("categoria"));
                    p.setImmagine(rs.getString("immagine"));
                    products.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

}
