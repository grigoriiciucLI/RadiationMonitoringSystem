package ui;

import model.RadiationReading;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReadingFormDialog extends JDialog {

    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final JTextField tfTimestamp = new JTextField(18);
    private final JTextField tfLevel = new JTextField(10);
    private final JComboBox<String> cbType = new JComboBox<>(new String[]{"Alpha","Beta","Gamma"});
    private final JComboBox<String> cbAlert = new JComboBox<>(new String[]{"Normal","Warning","Critical"});
    private final JTextArea taNotes = new JTextArea(3, 20);

    private boolean confirmed = false;
    private RadiationReading reading;

    /** Constructor for adding a new reading. */
    public ReadingFormDialog(Frame parent, int stationId) {
        this(parent, null, stationId);
    }

    /** Constructor for editing an existing reading. */
    public ReadingFormDialog(Frame parent, RadiationReading existing, int stationId) {
        super(parent, existing == null ? "Add Reading" : "Edit Reading", true);
        this.reading = existing;
        buildUI();
        if (existing != null) prefill(existing);
        pack();
        setLocationRelativeTo(parent);
    }

    private void buildUI() {
        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(new EmptyBorder(14, 14, 10, 14));
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints lc = lCons(), fc = fCons();

        addRow(form, "Timestamp (yyyy-MM-dd HH:mm):", tfTimestamp, lc, fc, 0);
        addRow(form, "Radiation Level (mSv):",         tfLevel,     lc, fc, 1);
        addRow(form, "Radiation Type:",                 cbType,      lc, fc, 2);
        addRow(form, "Alert Status:",                   cbAlert,     lc, fc, 3);

        lc.gridy = 4; lc.anchor = GridBagConstraints.NORTHWEST;
        form.add(new JLabel("Notes:"), lc);
        fc.gridy = 4;
        JScrollPane sp = new JScrollPane(taNotes);
        sp.setPreferredSize(new Dimension(220, 60));
        form.add(sp, fc);
        tfTimestamp.setText(LocalDateTime.now().format(DT_FMT));
        content.add(form, BorderLayout.CENTER);
        JButton btnSave   = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        btnSave.addActionListener(e -> onSave());
        btnCancel.addActionListener(e -> dispose());
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btns.add(btnSave); btns.add(btnCancel);
        content.add(btns, BorderLayout.SOUTH);
        setContentPane(content);
    }

    private void onSave() {
        String tsText = tfTimestamp.getText().trim();
        if (tsText.isEmpty()) { error("Timestamp is required."); return; }
        LocalDateTime ts;
        try { ts = LocalDateTime.parse(tsText, DT_FMT); }
        catch (DateTimeParseException ex) { error("Timestamp must be: yyyy-MM-dd HH:mm"); return; }
        String lvText = tfLevel.getText().trim();
        if (lvText.isEmpty()) { error("Radiation level is required."); return; }
        double level;
        try { level = Double.parseDouble(lvText); }
        catch (NumberFormatException ex) { error("Radiation level must be a number."); return; }
        if (level < 0) { error("Radiation level cannot be negative."); return; }

        if (reading == null) reading = new RadiationReading();
        reading.setTimestamp(ts);
        reading.setRadiationLevel(level);
        reading.setRadiationType((String) cbType.getSelectedItem());
        reading.setAlertStatus((String) cbAlert.getSelectedItem());
        reading.setNotes(taNotes.getText().trim());

        confirmed = true;
        dispose();
    }

    private void prefill(RadiationReading r) {
        if (r.getTimestamp() != null) tfTimestamp.setText(r.getTimestamp().format(DT_FMT));
        tfLevel.setText(String.valueOf(r.getRadiationLevel()));
        if (r.getRadiationType() != null) cbType.setSelectedItem(r.getRadiationType());
        if (r.getAlertStatus()   != null) cbAlert.setSelectedItem(r.getAlertStatus());
        taNotes.setText(r.getNotes() != null ? r.getNotes() : "");
    }

    private void addRow(JPanel p, String lbl, JComponent f,
                        GridBagConstraints lc, GridBagConstraints fc, int row) {
        lc.gridy = row; lc.anchor = GridBagConstraints.EAST;
        p.add(new JLabel(lbl), lc);
        fc.gridy = row; p.add(f, fc);
    }

    private GridBagConstraints lCons() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.insets = new Insets(4,0,4,8); c.anchor = GridBagConstraints.EAST;
        return c;
    }

    private GridBagConstraints fCons() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1; c.insets = new Insets(4,0,4,0);
        c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1.0;
        return c;
    }

    private void error(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validation Error", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isConfirmed() { return confirmed; }
    public RadiationReading getReading() { return reading; }
}