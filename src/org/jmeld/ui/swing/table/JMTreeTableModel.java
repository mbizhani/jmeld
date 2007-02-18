/*
   JMeld is a visual diff and merge tool.
   Copyright (C) 2007  Kees Kuip
   This library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.
   This library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.
   You should have received a copy of the GNU Lesser General Public
   License along with this library; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA  02110-1301  USA
 */
package org.jmeld.ui.swing.table;

import org.jdesktop.swingx.treetable.*;
import org.jmeld.util.node.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class JMTreeTableModel
       extends DefaultTreeTableModel
{
  private List<Column> columns;
  private JMDiffNode   root;

  public JMTreeTableModel(JMDiffNode root)
  {
    super(root);

    this.root = root;
    columns = new ArrayList<Column>();
  }

  public Object getChild(
    Object parent,
    int    index)
  {
    return ((JMDiffNode) parent).getChildAt(index);
  }

  public int getChildCount(Object parent)
  {
    return ((JMDiffNode) parent).getChildCount();
  }

  public Column addColumn(
    String  id,
    String  columnGroupName,
    String  columnName,
    Class   columnClass,
    int     columnSize,
    boolean editable,
    Color   background)
  {
    Column column;

    column = new Column(
        id,
        columns.size(),
        columnGroupName,
        columnName,
        columnClass,
        columnSize,
        editable,
        background);
    columns.add(column);

    return column;
  }
  
  public abstract Object getValueAt(
    Object objectNode,
    Column column);

  /*
  public abstract void setValueAt(
    Object value,
    Object objectNode,
    Column column);
  */

  public int getColumnSize(int columnIndex)
  {
    return getColumn(columnIndex).columnSize;
  }

  public String getColumnName(int columnIndex)
  {
    return getColumn(columnIndex).columnName;
  }

  public Class getColumnClass(int columnIndex)
  {
    Class clazz;

    clazz = getColumn(columnIndex).columnClass;
    if(clazz != null)
    {
      return clazz;
    }

    return super.getColumnClass(columnIndex);
  }

/*
  public Class getColumnClass(
    int    rowIndex,
    Column column)
  {
    return null;
  }

  public Class getColumnClass(
    int rowIndex,
    int columnIndex)
  {
    return getColumnClass(
      rowIndex,
      getColumn(columnIndex));
  }

  public Color getColumnBackground(int columnIndex)
  {
    return getColumn(columnIndex).background;
  }

  public String getColumnGroupName(int columnIndex)
  {
    return getColumn(columnIndex).columnGroupName;
  }
  */

  public int getColumnCount()
  {
    return columns.size();
  }

  public String getColumnId(int columnIndex)
  {
    return getColumn(columnIndex).id;
  }

  public TableCellRenderer getRenderer(int columnIndex)
  {
    return getColumn(columnIndex).renderer;
  }

  public TableCellEditor getEditor(int columnIndex)
  {
    return getColumn(columnIndex).editor;
  }

  public Column getColumn(int columnIndex)
  {
    return columns.get(columnIndex);
  }

  public boolean isCellEditable(
    int    rowIndex,
    Column column)
  {
    return column.isEditable();
  }

  public final boolean isCellEditable(
    int rowIndex,
    int columnIndex)
  {
    return isCellEditable(
      rowIndex,
      getColumn(columnIndex));
  }

  public final Object getValueAt(
    Object objectNode,
    int    columnIndex)
  {
    return getValueAt(
      objectNode,
      getColumn(columnIndex));
  }

/*
  public final void setValueAt(
    Object value,
    Object objectNode,
    int    columnIndex)
  {
    setValueAt(
      value,
      objectNode,
      getColumn(columnIndex));
  }
  */

  public class Column
  {
    private String            id;
    private int               columnIndex;
    private String            columnGroupName;
    private String            columnName;
    private Class             columnClass;
    private int               columnSize;
    private boolean           editable;
    private Color             background;
    private TableCellRenderer renderer;
    private TableCellEditor   editor;

    public Column(
      String  id,
      int     columnIndex,
      String  columnGroupName,
      String  columnName,
      Class   columnClass,
      int     columnSize,
      boolean editable,
      Color   background)
    {
      this.id = id;
      this.columnIndex = columnIndex;
      this.columnGroupName = columnGroupName;
      this.columnName = columnName;
      this.columnClass = columnClass;
      this.columnSize = columnSize;
      this.editable = editable;
      this.background = background;
    }

    public String getId()
    {
      return id;
    }

    public int getColumnIndex()
    {
      return columnIndex;
    }

    public String getColumnGroupName()
    {
      return columnGroupName;
    }

    public String getColumnName()
    {
      return columnName;
    }

    public Class getColumnClass()
    {
      return columnClass;
    }

    public int getColumnSize()
    {
      return columnSize;
    }

    public boolean isEditable()
    {
      return editable;
    }

    public Color getBackground()
    {
      return background;
    }

    public String toString()
    {
      return "column[id=" + id + "]";
    }

    public void setRenderer(TableCellRenderer renderer)
    {
      this.renderer = renderer;
    }

    public void setEditor(TableCellEditor editor)
    {
      this.editor = editor;
    }
  }
}