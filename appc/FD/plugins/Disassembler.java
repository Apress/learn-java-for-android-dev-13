package plugins;

import java.io.IOException;

import java.util.List;

public abstract class Disassembler
{
   public abstract List<String> disassemble(String filename) throws IOException;
}