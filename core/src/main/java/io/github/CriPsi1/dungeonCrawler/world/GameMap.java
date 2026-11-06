package io.github.CriPsi1.dungeonCrawler.world;

public class GameMap {
    private int tileSize;
    private int[][] tiles;
    public GameMap (int[][] tiles, int tileSize)
    {
        this.tiles = tiles;
        this.tileSize = tileSize;
        // create border map
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j <tiles[i].length; j++)
            {
                if (i == 0 || i == tiles.length-1 || j == 0 || j == tiles[i].length-1)
                {
                    tiles[i][j] = 1;
                }
            }
        }
    }
    // check map form
    public void printMap()
    {
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println();
        }
    }

    // take information
    public int getTileSize()
    {
        return tileSize;
    }

    public int getRows()
    {
        return tiles.length;
    }

    public int getColumns()
    {
        return tiles[0].length;
    }

    public int getTile(int row, int column)
    {
        return tiles[row][column];
    }

    // check if is a wall
    public boolean isWall(int row, int column)
    {
        if (row >= tiles.length || row < 0 || column >= tiles[0].length || column < 0)
        {
            return true;
        }
        return tiles[row][column] == 1;
    }

    // check px coordinate
    public boolean isWallAt(float x, float y)
    {
        int column = (int)Math.floor(x / tileSize);
        int row = (int) Math.floor(y/ tileSize);
        return isWall(row, column);
    }

    public boolean canMoveTo (float x, float y, float size)
    {
        int firstColumn = (int)Math.floor(x/tileSize);
        int lastColumn = (int)Math.floor((x+size - 0.001f)/tileSize);
        int firstRow = (int)Math.floor(y/tileSize);
        int lastRow = (int)Math.floor((y+size - 0.001f)/tileSize);
        for (int i = firstRow; i <= lastRow; i++)
        {
            for (int j = firstColumn; j <= lastColumn; j++)
            {
                if (isWall(i,j))
                {
                    return  false;
                }
            }
        }
        return true;
    }



}


