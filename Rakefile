require "rake/clean"
require "fileutils"

SRC_DIR = "src"
APP_DIR = "app"

task :default => [:styles, :list]

directory "assets"

stylesheets = FileList.new(["commons", "banner"].map { |stylesheet|
  "#{SRC_DIR}/assets/#{stylesheet}.scss"
})


desc "Render stylesheets"
task :styles => stylesheets do
  require "sass"

  assets_dir = "#{APP_DIR}/assets"
  mkdir_p(assets_dir) unless File.directory? assets_dir

  engine = Sass::Engine.for_file(stylesheets[0], # should be commons.scss
                                 { :syntax => :scss,
                                   :style => :expanded })
  commons = File.new("#{APP_DIR}/assets/commons.css", "w")
  commons.write(engine.render)
  commons.close
end

desc "Render the list"
task :list => :styles do
  require "liquid"
  require_relative "#{SRC_DIR}/include_tag"

  VIEW_DIR = "#{SRC_DIR}/views"
  DATA_DIR = "#{SRC_DIR}/data"

  # username-fullname table
  PROFS = Psych.load_file("#{DATA_DIR}/profs.yaml")

  # list of opportunities
  # will just read the first "document", STEC 2500, for now
  COURSE  = Psych.load_file("#{DATA_DIR}/opps.yaml")
  opps = COURSE["opportunities"]
  subjects = opps.map { |opp| opp["disciplines"] }.flatten.uniq.sort

  # assign professor ids to names
  opps.map { |opp| opp["professors"].map! { |prof| PROFS[prof] }}

  # sort opportunities based on discipline.
  # by design, opportunities crossing multiple disciplines will be
  # repeated. `obs`, or "opportunities by subject", is an array of
  # hashes for the sake of the templating system.
  #
  # obs contains:
  #   * name: String (name of the subject)
  #   * opportunities: Array of Hashes (representing each opp in a subject)
  obs = Array.new
  for subject in subjects
    curr_sub = Hash.new
    curr_sub["name"] = subject
    curr_sub["opportunities"] = Array.new

    for opp in opps
      curr_sub["opportunities"].push opp if opp["disciplines"].include? subject
    end

    obs.push curr_sub
  end

  template = Liquid::Template.parse(File.read("#{VIEW_DIR}/index.html"))
  index = File.new("#{APP_DIR}/index.html", "w")

  index.write(template.render({ "subjects" => obs }))
  index.close
end

CLOBBER.include("app/*")
CLOBBER.include("app/")
