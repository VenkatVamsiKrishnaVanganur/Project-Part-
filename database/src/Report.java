import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/report")
public class Report extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Report() {
    }

    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&user=root&password=pass1234");
            System.out.println(connect);
        }
    }

    public boolean database_login(String userName, String password) throws SQLException {
        try {
            connect_func("root", "password");
            String sql = "select * from user where user_name = ?";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("failed login");
            return false;
        }
    }

    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb?" + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    public List<Client> geteasyClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "select distinct (q.client_id) from quote_request q where q.quote_id not in (select q.quote_id from quote_response q where q.status='negotiate')";
        List<Integer> ids = new ArrayList<>();
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("client_id");
            ids.add(id);
        }
        System.out.println("ids " + ids.size());
        resultSet.close();
        disconnect();
        for (int id : ids) {
            Client client = new userDAO().getClientId(id);
           
            clients.add(client);
        }
        System.out.println("Easy clients : " + clients.toString());
        return clients;
    }

    public List<Client> getbigClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Client client = null;
        String sql = "WITH ClientTreeCounts AS (\n" + "    SELECT\n" + "       "
        		+ " C.client_id,\n" + "       "
        				+ " C.firstName,\n" + "    "
        						+ "    C.lastName,\n" + "    "
        								+ "    C.email,\n" + "    "
        										+ "    C.phone,\n" + "     "
        												+ "   COUNT(T.tree_id) AS tree_count\n" + " "
        														+ "   FROM\n" + "     "
        																+ "   Client C\n" + "    JOIN\n" + "        quote_request Q ON C.client_id = Q.client_id\n" + "    JOIN\n" + "        Tree T ON Q.quote_id = T.quote_id\n" + "    GROUP BY\n" + "        C.client_id, C.firstName, C.lastName\n" + ")\n" + "SELECT\n" + "    CTC.client_id,\n" + "    CTC.firstName,\n" + "    CTC.lastName,\n" + "    CTC.tree_count,\n" + "    CTC.email,\n" + "    CTC.phone\n" + "FROM\n" + "    ClientTreeCounts CTC\n" + "WHERE\n" + "    CTC.tree_count = (SELECT MAX(tree_count) FROM ClientTreeCounts);";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int client_id = resultSet.getInt("client_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            long phone = resultSet.getLong("phone");
            String tree_count = resultSet.getString("tree_count");
            client = new Client(client_id, firstName, lastName, null,email, phone, tree_count);
            clients.add(client);
        }
        resultSet.close();
        disconnect();
        return clients;
    }

    public List<Quote> getoneTreeQuote() throws SQLException {
        List<Quote> quotes = new ArrayList<>();
        Quote quote = null;
        String sql = "select t.quote_id,q.client_id, q.propose_date,q.status,q.price,q.start_date,q.end_date,q.note from tree t join quote_request q on q.quote_id=t.quote_id group by t.quote_id  having count(t.quote_id)<2";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int quote_id = resultSet.getInt("quote_id");
            int client_id = resultSet.getInt("client_id");
            String note = resultSet.getString("note");
            Date propose_date = Date.valueOf(resultSet.getString("propose_date"));
            Date start_date = Date.valueOf(resultSet.getString("start_date"));
            Date end_date = Date.valueOf(resultSet.getString("end_date"));
            double price = resultSet.getDouble("price");
            String qstatus = resultSet.getString("status");
            quote = new Quote(quote_id, client_id, propose_date, note, qstatus, price, start_date, end_date);
            quotes.add(quote);
        }
        return quotes;
    }

    public Client toClientList(ResultSet resultSet) throws SQLException {
        Client client = null;
        while (resultSet.next()) {
            int clientId = resultSet.getInt("client_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            long phone = Long.parseLong(resultSet.getString("phone"));
            client = new Client(clientId, firstName, lastName, null, email, phone, null);
        }
        resultSet.close();
        return client;
    }

    public List<Client> getprospectiveClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Client client = null;
        String sql = "select * from client c where c.client_id not in (\n" + "select  q.client_id from client c  join quote_request q on c.client_id = q.client_id join orders o on q.quote_id = o.quote_id  where q.status='accepted' group by q.client_id);";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int client_id = resultSet.getInt("client_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            long phone = resultSet.getLong("phone");
            String cardNumber = String.valueOf(resultSet.getString("credit_card_info"));
            System.out.println("creditcard : " + cardNumber);
            client = new Client(client_id, firstName, lastName, null, email, phone, cardNumber);
            clients.add(client);
        }
        return clients;
    }

    public List<Bill> getoverDueBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        Bill bill = null;
        String sql = "SELECT B.bill_id, B.order_id, B.total_amount, B.status, B.note, B.generated_bill_date, B.bill_paid_date\n" + "FROM Bill B\n" + "JOIN Orders O ON B.order_id = O.order_id\n" + "WHERE B.bill_paid_date IS NULL\n" + "  AND B.generated_bill_date IS NOT NULL\n" + "  AND CURRENT_DATE > DATE_ADD(B.generated_bill_date, INTERVAL 7 DAY);";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int billId = resultSet.getInt("bill_id");
            int orderId = resultSet.getInt("order_id");
            String status = resultSet.getString("status");
            double totalPrice = resultSet.getDouble("total_Amount");
            String note = resultSet.getString("note");
            Date generated_on = Date.valueOf(resultSet.getString("generated_bill_date"));
            bill = new Bill(billId, orderId, totalPrice, status, note, generated_on, null);
            bills.add(bill);
        }
        return bills;
    }

    public List<Client> getbadClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Client client = null;
        String sql = "SELECT C.client_id, C.firstName, C.lastName, C.email , C.phone\n" + "FROM Client C\n" + "LEFT JOIN quote_request Q ON C.client_id = Q.client_id\n" + "LEFT JOIN Orders O ON Q.quote_id = O.quote_id\n" + "LEFT JOIN Bill B ON O.order_id = B.order_id\n" + "WHERE B.generated_bill_date is not null and B.bill_paid_date is null and CURRENT_DATE > DATE_ADD(B.generated_bill_date, INTERVAL 7 DAY);";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int client_id = resultSet.getInt("client_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            long phone = resultSet.getLong("phone");
            client = new Client(client_id, firstName, lastName, null, email, phone, null);
            clients.add(client);
        }
        resultSet.close();
        disconnect();
        return clients;
    }

    public List<Client> getgoodClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Client client = null;
        String sql = "SELECT C.client_id, C.firstName, C.lastName, C.email , C.phone\n" + "FROM Client C\n" + "LEFT JOIN quote_request Q ON C.client_id = Q.client_id\n" + "LEFT JOIN Orders O ON Q.quote_id = O.order_id\n" + "LEFT JOIN Bill B ON O.order_id = B.order_id\n" + "WHERE B.generated_bill_date is not null and B.bill_paid_date is not null and CURRENT_DATE <= DATE_ADD(B.generated_bill_date, INTERVAL 1 DAY);";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int client_id = resultSet.getInt("client_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            long phone = resultSet.getLong("phone");
            client = new Client(client_id, firstName, lastName, null, email, phone, null);
            clients.add(client);
        }
        resultSet.close();
        disconnect();
        return clients;
    }

    public List<Tree> gethighestTree() throws SQLException {
        List<Tree> trees = new ArrayList<>();
        Tree tree = null;
        String sql = "WITH RankedTrees AS (\n" + "    SELECT\n" + "        T.tree_id,\n" + "        T.size,\n" + "        T.height,\n" + "        RANK() OVER (ORDER BY T.height DESC) AS tree_rank\n" + "    FROM\n" + "        Tree T\n" + "    WHERE\n" + "        T.NearHouse = TRUE\n" + ")\n" + "SELECT\n" + "    RT.tree_id,\n" + "    RT.size,\n" + "    RT.height\n" + "FROM\n" + "    RankedTrees RT\n" + "WHERE\n" + "    RT.tree_rank = 1;";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int tree_id = resultSet.getInt("tree_id");
            String size = resultSet.getString("size");
            double height = resultSet.getDouble("height");
            tree = new Tree(tree_id, 0, size, height, "", false, "");
            trees.add(tree);
        }
        resultSet.close();
        disconnect();
        return trees;
    }
    
    public List<Client> getStatistics() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Client client = null;
        String sql = "SELECT\r\n"
        		+ "    C.client_id,\r\n"
        		+ "    C.firstName,\r\n"
        		+ "    C.lastName,\r\n"
        		+ "    COUNT(T.tree_id) AS total_trees,\r\n"
        		+ "    SUM(Q.price) AS total_due_amount,\r\n"
        		+ "    SUM(B.total_amount) AS total_paid_amount,\r\n"
        		+ "    Q.end_date AS work_done_date\r\n"
        		+ "FROM\r\n"
        		+ "    Client C\r\n"
        		+ "JOIN\r\n"
        		+ "    quote_request Q ON C.client_id = Q.client_id\r\n"
        		+ "JOIN\r\n"
        		+ "    Orders O ON Q.quote_id = O.quote_id\r\n"
        		+ "JOIN\r\n"
        		+ "    Tree T ON Q.quote_id = T.quote_id\r\n"
        		+ "LEFT JOIN\r\n"
        		+ "    Bill B ON O.order_id = B.order_id\r\n"
        		+ "WHERE\r\n"
        		+ "    B.generated_bill_date is not null\r\n"
        		+ "GROUP BY\r\n"
        		+ "    C.client_id, Q.end_date";
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int client_id = resultSet.getInt("client_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String total_trees = resultSet.getString("total_trees");
            String total_paid_amount = resultSet.getString("total_paid_amount");
            long total_due_amount = resultSet.getLong("total_due_amount");
            String work_done_date = resultSet.getString("work_done_date");
            client = new Client(client_id, firstName, lastName, total_trees, total_paid_amount, total_due_amount, work_done_date);
            clients.add(client);
        }
        resultSet.close();
        disconnect();
        return clients;
    }
}
