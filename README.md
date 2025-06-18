# 🌡️ Advanced Temperature Converter (Java Swing GUI)

This is a **Java Swing desktop application** that allows users to convert temperatures between **Celsius**, **Fahrenheit**, and **Kelvin**. It includes a **real-time visual temperature dial** (graph-like), **conversion history with timestamps**, **export to file**, and **climate-based suggestions** for user comfort.

---

## 🚀 Features

- 🔁 Convert between **Celsius**, **Fahrenheit**, and **Kelvin**
- 📈 **Graph-like Temperature Dial** — a circular gauge that visually shows the current temperature using a needle and fill
- 🕒 Real-time **conversion history log** with timestamps
- 💡 **Suggestions** based on temperature ranges (e.g. cold, pleasant, hot)
- 📤 Export conversion history to `temperature_history.txt`
- 🧼 **Clear fields** and reset the dial
- 🖱️ Easy to use with input from any unit (C/F/K)

---

## 🧰 Built With

- **Java** (JDK 8+)
- **Swing** for GUI (`JFrame`, `JPanel`, `JTextField`, `JComboBox`, `JTextArea`)
- **Java AWT** for custom dial graphics
- **Eclipse IDE** (recommended)

---

## 📁 Project Structure

```
TemperatureConverter/
├── src/
│   └── AdvancedTempConverter.java  ← Main GUI + logic
├── bin/                            ← Compiled .class files (auto-generated)
├── temperature_history.txt         ← Exported log (after using Export)
├── .classpath / .project           ← Eclipse metadata
├── .gitignore
```

---

## 📈 Temperature Dial (Graphical View)

The app includes a **circular dial** that:
- Fills with color depending on the temperature
- Shows temperature range from **-30°C to +70°C**
- Moves a **needle** to indicate current temperature
- Includes **tick marks and labels** like a thermometer gauge

---

## 🛠️ How to Run (Eclipse)

### 💻 Steps:

1. Open Eclipse → `File → Import → General → Existing Projects into Workspace`
2. Browse to project path:  
   `C:\Users\gudel\git\repository\TemperatureConverter`
3. ✅ Finish
4. Open `AdvancedTempConverter.java` from `src`
5. Right-click → `Run As` → `Java Application`

---

## 🧪 How to Use

1. Choose the **input unit** from the dropdown (Celsius / Fahrenheit / Kelvin)
2. Enter the temperature in the corresponding input field
3. Click **Convert**
4. View:
   - Converted temperatures
   - Temperature **graph dial**
   - Climate **suggestion**
   - **Timestamped history log**
5. Use **Export History** to save logs to a `.txt` file
6. Use **Clear** to reset all fields and the dial

---

## 📜 Sample History Log Output

```
[18-06-2025 10:15:33] 25.00°C = 77.00°F, 298.15K
[18-06-2025 10:18:47] 100.00°C = 212.00°F, 373.15K
```

---

## 🗂️ Git Setup (Example)

```bash
git init
git remote add origin https://github.com/Naveen2-G/PRODIGY_SD_1.git
git add .
git commit -m "Initial commit: Add Advanced Temperature Converter"
git push origin main1
```

---

## 👨‍💻 Author

**Naveen Gudela**  
- GitHub: [@Naveen2-G](https://github.com/Naveen2-G)
- Linkedin:(https://www.linkedin.com/in/naveen53/)

---

## 📝 License

This project is open-sourced under the **MIT License** .
