public class Map {
    private char[][] charMap;
    private float[][] costMap;

    /*Initiating MAP to play*/
    public Map(char[][] charMap){
        this.charMap = charMap;
        costMap = generateCostMap();
    }
    /* Return Map with what place we have in each position*/
    public char[][] getCharMap(){
        return charMap;
    }
    /* Return the map with which cost has each position */
    public float[][] getCostMap(){
        return costMap;
    }

    /*Generate the CostMap with the Map in ASCII*/
    private float[][] generateCostMap(){
        float[][] costMap = new float[charMap.length][charMap[0].length];        
        
        float cost = -1;
        for(int row=0; row < charMap.length; row++){
            for(int col=0; col < charMap[0].length; col++){
                switch(charMap[row][col]){
                    //Coste general -5 + (lo que genera el comerciante respecto a la casilla donde está)
                    //ponemos coste 0 en la montaña como centinela
                    case 'M': cost= 0;// TODO: Define cost corresponding to "Mountain" ("Muntanya")
                    break;
                    case 'N': cost= -5;// TODO: Define cost corresponding to "Empty" ("Buit")
                    break;
                    case 'A': cost= -5+1;// TODO: Define cost corresponding to "Village" ("Aldea")
                    break;
                    case 'P': cost= -5+3;// TODO: Define cost corresponding to "Town" ("Poble")
                    break;
                    case 'C': cost= -5+4.5F;// TODO: Define cost corresponding to "City" ("Ciutat/capital")
                    break;
                }
                costMap[row][col] = cost;
            }
        }

        return costMap;
    }

    // Gets the String of the Map
    public String toString(){
        String text = "";

        for (char[] row : charMap) {
            for (char cell : row){
                text+=cell+" ";
            }
            text+="\n";
        }

        // Remove last enter
        text = text.substring(0, text.length()-1);

        return text;
    }
}
