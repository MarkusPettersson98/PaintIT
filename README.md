# TDA367
Objektorienterat Programmeringsprojekt

## Innehåll
* [Komma igång](#komma-igång)
* [git](#git)
  * [Hjälp](#hjälp)
  * [Tips & tricks](#tips--tricks)
  * [Länkar](#användabara-länkar-för-att-komma-igång-med-git-)

## Komma igång
1. Klona repositorien till din dator
```
git clone https://github.com/DorisIT/TDA367.git
```
2. Klart!

## git

### Vanliga git-kommandon :computer:
```
git status
git add
git commit
```

#### Hjälp
Använd kommandot "--help" eller "-h" för att få en utförlig förklaring av ett git-kommando och hur det används.

Detta kommer att öppna dokumentationen för det aktuella kommandot.
```
git *kommando* --help
```

Detta kommer att skriva ut dokumentationen direkt i terminalen, så att du kan fortsätta hacka asap!
```
git *kommando* -h
```

**Exempel**

```
$ git help -h
usage: git help [--all] [--guides] [--man | --web | --info] [<command>]

    -a, --all             print all available commands
    -g, --guides          print list of useful guides
    -m, --man             show man page
    -w, --web             show manual in web browser
    -i, --info            show info page   
    
    
```
***
### Tips & tricks

#### git commit
Om du vill stagea och commita alla dina ändringar med ett kommando, look no further! Följande kommando gör exakt detta.
```
git commit -am "ditt meddelande"
```

#### Desperata åtgärder

##### Quitting Vim
Om du råkade commita utan meddelande kommer git att kasta in dig i den fruktade text-editorn Vim ([relevant meme](https://stackoverflow.blog/2017/05/23/stack-overflow-helping-one-million-developers-exit-vim/)). För att ta dig ur Vim, tryck på "esc"-knappen tills du är säker på att du inte befinner dig i "insert mode". Skriv sedan in  `:wq` och tryck "enter".


##### Återställa lokal repo
Om du gjort lokala ändringar men vill uppdatera till senaste branchen kan det hända att du blir nekad av git med ett felmeddelande. 
```
error: Your local changes to the following files would be overwritten by merge:
someFolder/someSubFolder/changedFile.extension
Please, commit your changes or stash them before you can merge.
```
För att komma runt detta kan du tvinga git att skriva över din lokala kodbas och uppdatera branchen.
```
git fetch origin | git reset --hard origin/master
```
***
### Användabara länkar för att komma igång med git 🐱‍💻

* [git på 20 minuter (video)](https://www.youtube.com/watch?v=0fKg7e37bQE)
* [Interaktiv tutorial](https://try.github.io/levels/1/challenges/1)
* [git - the simple guide](http://rogerdudler.github.io/git-guide/)
* [Dokumentation](https://git-scm.com/docs)
* [Atomic Commits (viktigt!)](https://www.freshconsulting.com/atomic-commits/)
* [git Cheat Sheet](https://www.git-tower.com/blog/git-cheat-sheet/)
