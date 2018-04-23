STEC Research Portal Guide
==========================

Introduction
------------

This project was requested by [Dr. Saum][msaum] to create a more friendly
interface to listings of the STEC research opportunities. [Dr. Cengiz
Günay][cgunay] is the one sponsoring the project with his Software Dev. II
class. Currently (2018-04-23), there is a live demonstration at
<https://etc.cardboard.software/stec>.

As of now, there are currently two listings for STEC research projects: a
private one on the MyGGC pages and a public one on the GGC Web site. They
are not consistent and the aim is to both pick one to be the main list and
to make it more friendlier to a student via searching mechanism. The end
goal here is to come up with a mockup of an idea of a possible interface
later down the road. The main idea for this is that instead of searching for
what you want, you filter out what you don't want.

[msaum]: http://ggc.edu/about-ggc/directory/michael-saum
[cgunay]: http://ggc.edu/about-ggc/directory/cengiz-gunay

### Credits (aka Who to Blame)

You're likely here because you just inherited this project either by choice
or were ~~chosen~~ blessed by the Great Random Number Generator in the Sky
for your ITEC 3870 class.  In that case, congratulations! You're either
going to curse our names for this or be glad you're doing a rewrite.

* [Dave Chesser][dchesser] designed the site, rigged the build automation,
  and provides hosting.
* [Austin Bond][abond1] wrote usability tests, built the filter, and fixed
  some otherwise pressing bugs.

[dchesser]: https://github.com/dchesser
[abond1]: https://github.com/abond1

How to Use
----------

### Building

This project is 99% a Rakefile, so simply run:

    $ rake

The default task, `list`, will build both the stylesheets and generate the
list from the data. Building only the stylesheets is possible with the
`styles` task. To see what can be done here, run:

    $ rake -T

### Research Opportunities

Research opportunities are stored in a YAML document located in
`src/data/opps.yaml`.  It was anticipated to store multiple STEC courses,
hence why the top-level format goes by:

    course: "STEC 2500"
    opportunities:
      - ...
      - ...
      - ...
    course: "STEC 4500"
        opportunities:
      - ...
      - ...
      - ...

The `opportunities` list then into the following format:

    opportunities:
      - disciplines: ["Biology"]
        professors: ["asmith1"]
        description: "Investigate the effects of milk on bones"
      - disciplines: ["Biology", "Psychology"]
        professors: ["jdoe", "psherman42"]
        description: "Study of fish and their habitats"
	  - ...

`disciplines` is an array of strings; in the case of cross-subject
collaborations, multiple disciplines can be included. `professors` is also
an array of strings based off of professor's electronic mail address. A
key-value database from these shorthand names to full names is located in
`src/data/profs.yaml`. `description` is a simple string explaining the
opportunity. The opportunities data file can be extended to contain short
and long descriptions for the templating system.

### Stylesheets

The look and feel of the project is based on stylesheets located in
`src/assets`. The syntax used is SCSS, interpreted by the [Sass][sass] gem.
`commons.scss` is the primary stylesheet as it imports the rest. The
classes are *intended* to be in accordance to [BEM][bem] class names.

[sass]: http://sass-lang.com/
[bem]:  https://en.bem.info/methodology/css/

### Templates

Only one page exists in this project: `src/views/index.html`. It uses the
[Liquid][gh:shopify/liquid] templating system to enter values. Hopefully the
Rakefile is commented decently enough to understand the procedure.

[gh:shopify/liquid]: https://github.com/Shopify/liquid

### Deploying

To deploy, simply call `rake` and move the new `app/` directory to your Web
host of choice. We'd generally recommend `rsync` to mirror the remote
location to `app/`.
