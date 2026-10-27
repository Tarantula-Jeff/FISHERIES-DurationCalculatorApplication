# Fisheries Duration Calculator

A desktop application for calculating the duration of fisheries and vessel trips. Enter departure and arrival times to receive a clear trip-duration result in hours and minutes, including trips that cross midnight.

The project was created to make duration calculations faster and more reliable when completing trip and data-collection records during fisheries work.

## Features

- Clean Java Swing desktop interface
- Departure and arrival time inputs in 24-hour `HH:mm` format
- Accurate duration calculation using Java's `java.time` API
- Automatic handling for overnight trips
- Friendly validation messages for invalid time entries
- Clear/reset action for consecutive calculations
- Welcome screen and modern responsive window layout

## Example

| Departure | Arrival | Result |
| --- | --- | --- |
| `08:30` | `16:45` | `8 hours 15 minutes` |
| `23:30` | `01:15` | `1 hour 45 minutes` |

## Technologies

- Java
- Java Swing
- Java Time API (`LocalTime`, `Duration`, `DateTimeFormatter`)
- Apache NetBeans project structure

## Requirements

- Java Development Kit (JDK) 17 or later
- Apache NetBeans (recommended), or another Java IDE

## Run with NetBeans

1. Open the project folder in Apache NetBeans.
2. Build the project.
3. Run the `fisheries.BEGIN` main class.

## Run from the Command Line

From the project root, compile the source files:

```bash
javac -d out src/fisheries/*.java
```

Then run the application:

```bash
java -cp out fisheries.FISHERIES
```

## Project Structure

```text
src/fisheries/
├── FISHERIES.java  # Application entry point
├── BEGIN.java      # Welcome screen
└── MAINFRAME.java  # Duration-calculator interface and logic
```

## Author

Jeffrey Ackah
