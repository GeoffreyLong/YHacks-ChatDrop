#!/bin/bash

if [ $# -ne 1 ]; then
	echo "Usage - ./automerge.sh BRANCHNAME"
	exit 1;
fi

git checkout -b $1
git fetch
git rebase origin/master
if [ $? -ne 0 ]; then
	echo "Rebase failed. Please resolve conflicts and finish merge manually."
	exit 1;
fi
git push -f
git checkout master
git merge $1
git push
git checkout $1



