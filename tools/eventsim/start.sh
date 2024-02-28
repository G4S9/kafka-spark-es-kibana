# generate events from (-f) 30 days ago until (-e) now + 1 day
./eventsim -c example-config.json -k localhost:9094 -n 10000 --continuous -f 10 -e "$(date -d "+1 day" +%Y-%m-%dT%H:%M:%S)"
