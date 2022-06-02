package LOGIC.blocks;

import LOGIC.Coordinate;

public class Block {
    public BlockType blockType;
    public Coordinate coordinate;

    public Block() {
    }

    public Block(BlockType blockType, Coordinate coordinate) {
        this.blockType = blockType;
        this.coordinate = coordinate;
    }
}
