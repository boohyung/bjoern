#!/bin/bash
#
# Arguments:
# $1 - binary (passed to Radare
# $2 - database name (optional, passed to Importer)
#
# Note: All duplicate rows are removed before import.

BASEDIR=$(dirname "$0")
pushd $BASEDIR

TMP=$(mktemp -d)
trap "rm -rf $TMP" EXIT

projects/radare2csv/radare2csv.sh $1 -outdir $TMP || exit 1

tail -n+2 $TMP/nodes.csv | sort -r | uniq > $TMP/nodes.csv_
tail -n+2 $TMP/edges.csv | sort -r | uniq > $TMP/edges.csv_

head -n 1 $TMP/nodes.csv > $TMP/nodeHead.csv
head -n 1 $TMP/edges.csv > $TMP/edgeHead.csv

cat $TMP/nodeHead.csv > nodes.csv
cat $TMP/nodes.csv_ >> nodes.csv

cat $TMP/edgeHead.csv > edges.csv
cat $TMP/edges.csv_ >> edges.csv

if [ -z $2 ]
then
	projects/octopus/octopus-csvimport.sh
else
	projects/octopus/octopus-csvimport.sh -dbname $2
fi

popd
