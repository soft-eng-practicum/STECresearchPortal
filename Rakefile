require "rake/clean"
require "fileutils"

SRC_DIR = "src"
APP_DIR = "app"

task :default => :styles

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

CLOBBER.include("app/*")
CLOBBER.include("app/")
