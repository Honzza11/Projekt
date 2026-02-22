# Dobrodružství Underground Rappera

**Dobrodružství Underground Rappera** je textová adventura v Javě, kde se vžijete do role začínajícího rappera. Vaším cílem je vypracovat se z nuly, psát texty, tvořit skladby, bojovat ve freestyle battlech a nakonec ovládnout celou scénu a vystoupit na legendárním festivalu Rolling Loud Atlanta 2030.

## Ovládání hry

Hra se ovládá pomocí textových příkazů zadávaných do konzole. Zde je seznam základních příkazů:

- `jdi [místo]` – Přesune tě do sousední lokace (např. `jdi Ulice`).
- `prozkoumej` – Vypíše podrobný popis aktuální lokace a předmětů v ní.
- `vezmi [předmět]` – Sebere předmět ze země a vloží ho do inventáře.
- `mluv [postava]` – Zahájí rozhovor s postavou v lokaci.
- `inventar` – Ukáže vše, co máš právě u sebe.
- `info` – Vypíše tvé aktuální statistiky (peníze, reputaci, vybavený mikrofon).
- `ukoly` – Seznam tvých aktuálních cílů a úkolů.
- `napsat_text` – Pokusíš se napsat text pro svou budoucí skladbu.
- `vytvoritskladbu` – Spojí tvůj text a beat do hotové skladby.
- `publikuj` – Pokusíš se vydat skladbu v rádiu nebo studiu.
- `freestyle` – Vyzveš ostatní rappery na souboj v rýmování.
- `vystoupit` – Odehraješ koncert, pokud máš hotovou skladbu.
- `kup [předmět]` – Koupíš předmět u obchodníka (např. mikrofon nebo beat).
- `pouzij [předmět]` – Použiješ vybraný předmět z inventáře.
- `napoveda` – Zobrazí nápovědu přímo ve hře.
- `konec` – Uloží (pokud je implementováno) a ukončí hru.

## Jak hru spustit

### Spuštění z terminálu
1. Stáhněte si soubor `Projekt.jar`.
2. Otevřete terminál ve složce se souborem.
3. Spusťte hru příkazem:
   ```bash
   java -jar Projekt.jar
   ```

### Spuštění v IDE (IntelliJ IDEA)
1. Otevřete složku jako projekt v IntelliJ IDEA.
2. Najděte soubor `src/Main.java`.
3. Klikněte pravým tlačítkem na třídu `Main` a vyberte **Run 'Main.main()'**.
