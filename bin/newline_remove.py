# https://stackoverflow.com/questions/37682955/how-to-delete-empty-lines-from-a-txt-file
with open('input.txt') as infile, open('output.txt', 'w') as outfile:
    for line in infile:
        if not line.strip(): continue  # skip the empty line
        outfile.write(line)  # non-empty line. Write it to output