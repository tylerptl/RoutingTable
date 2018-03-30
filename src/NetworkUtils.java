import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Wade Ashby
 * @version 3/20/2018
 */
public class NetworkUtils {
    private String filename;
    //    nodeNames and connections are parallel arrays that contain the name and connection info for the network
    private String[] nodeNames;   // array of all the names of the nodes in the network
    private String[] connections; // array of all the connection information for each node


    /**
     * Constructor to build a NetworkUtils Object from a filename.
     * The file should have the first line as a set of nodes.
     * Then there should be a line for each node in the Network followed by =
     * then a pair of all of its connections.  Example file would look like<p>
     *   nodes={u,v,w,x,y,z}<br>d
     *   u=(v,2),(w,5),(x,1)<br>
     *   v=(u,2),(x,2),(w,3)<br>
     *   w=(u,5),(v,3),(x,3),(y,1),(z,5)<br>
     *   x=(u,1),(v,2),(w,3),(y,1)<br>
     *   y=(w,1),(x,1),(z,2)<br>
     *   z=(w,5),(y,2)<br>
     *   </p>
     * In the above example the nodes are u, v, w, x, y, and z.  The node u
     * has connections to v, w, and x with costs of 2, 5, and 1 respectively.
     *
     * @param filename The full filename (path and filename) to the file.
     */
    public NetworkUtils(String filename) {
        this.filename = filename;
        parseFile();
    }

    /**
     * Split to its own method in case the file format changed.d
     */
    private void parseFile(){
        Scanner fileIn;
//         The try is to catch the error in case the file is not found.
        try {
            fileIn = new Scanner(new File(this.filename));
//            The split will separate the list of nodes on the first line into a String array
//            with each element being a node name
            this.nodeNames = fileIn.nextLine().split("=")[1].replace("{","").replace("}","").split(",");
            this.connections = new String[this.nodeNames.length];
            while(fileIn.hasNextLine()) {
//                nodeInfo stores the node it is processing at index 0
                String[] nodeInfo = fileIn.nextLine().split("=");
                boolean found = false;
                int i = 0;
                while(!found && i<this.connections.length) {
                    if (this.nodeNames[i].equalsIgnoreCase(nodeInfo[0])) {
                        found = true;
//                        align the connection information from nodeInfo with the correct position in the array
                        this.connections[i] = nodeInfo[1];
                    }
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates printable string of node names and connection information.
     *
     * @return printable string
     */
    @Override
    public String toString(){
        return Arrays.toString(this.nodeNames)+"\n"+Arrays.toString(this.connections);
    }

    /**
     * Returns the string array of the connection information
     *
     * @return String array of the connection information
     */
    public String[] getConnections() {
        return connections;
    }

    /**
     * Returns the string array of the node names
     *
     * @return String array of the node names
     */
    public String[] getNodeNames() {
        return nodeNames;
    }

}
