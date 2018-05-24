---
- hosts: postgres
  become: yes
  gather_facts: no

  tasks:
  - name: ensure apt cache is up to date
    apt: update_cache=yes

  - name: add repo for java 8
    apt_repository: repo='ppa:webupd8team/java' state=present

  - name: set licence selected
    shell: /bin/echo debconf shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
    sudo: yes

  - name: set licence seen
    shell: /bin/echo debconf shared/accepted-oracle-license-v1-1 seen true | /usr/bin/debconf-set-selections
    sudo: yes

  - name: install java 8
    apt: name=oracle-java8-installer state=latest update-cache=yes force=yes
    sudo: yes

