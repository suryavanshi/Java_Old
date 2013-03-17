import java.rmi.Remote;
import java.rmi.RemoteException;

public interface atminterface extends Remote {
public int verify(atmuser u) throws RemoteException;
public atmuser getbalance(atmuser u)
       throws RemoteException;
public atmuser withdraw(int money,atmuser u)
       throws RemoteException;
}
    