
package proyfinalbd2;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

    public MultiLineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText(value == null ? "" : value.toString());

        // Ajusta colores si la celda está seleccionada
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        // Ajusta altura de fila
        setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
        int preferredHeight = getPreferredSize().height;
        if (table.getRowHeight(row) != preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }

        return this;
    }
}
