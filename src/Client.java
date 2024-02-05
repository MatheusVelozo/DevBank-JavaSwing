import java.util.Arrays;
import java.util.Objects;

public class Client {
    //ATRIBUTOS DA CLASSE CLIENTE.
    private String name;
    private String dateOfBirth;
    private String cpf;
    protected static char[] password;
    private String accountType;
    private Integer accountNumber;
    private Double balance;
    private Boolean status;

    public Client() {

    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public static char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        Client.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getCpf(), client.getCpf()) && Arrays.equals(getPassword(), client.getPassword()) && Objects.equals(getAccountNumber(), client.getAccountNumber()) && Objects.equals(getBalance(), client.getBalance()) && Objects.equals(getStatus(), client.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf(), getAccountNumber(), getStatus());
    }
}
